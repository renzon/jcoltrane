package net.sf.jColtrane.descriptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.xml.sax.SAXException;

import net.sf.jColtrane.annotations.args.Attribute;
import net.sf.jColtrane.annotations.args.AttributeMap;
import net.sf.jColtrane.annotations.args.Body;
import net.sf.jColtrane.annotations.args.CurrentBranch;
import net.sf.jColtrane.annotations.args.GeneralUseMap;
import net.sf.jColtrane.annotations.args.LocalName;
import net.sf.jColtrane.annotations.args.Tag;
import net.sf.jColtrane.annotations.args.Uri;
import net.sf.jColtrane.conditions.ConditionalMethod;
import net.sf.jColtrane.handler.ContextVariables;

public class ExecutionDescriptor  {
	private List <Method> startDocumentMethodsList;
	private List <Method> endDocumentMethodsList;
	private List <ConditionalMethod> startElementMethodsList;
	private List <ConditionalMethod> endElementMethodsList;
	
	
	

	public ExecutionDescriptor() {
		startDocumentMethodsList=new LinkedList<Method>();
		endDocumentMethodsList=new LinkedList<Method>();
		startElementMethodsList=new LinkedList<ConditionalMethod>();
		endElementMethodsList=new LinkedList<ConditionalMethod>();
	}
	
	public void sortConditionalMethods(){
		this.sortElements(startElementMethodsList);
		this.sortElements(endElementMethodsList);
	}
	
	private void sortElements(List<ConditionalMethod> list){
		Collections.sort(list);
	}
	
	public void addStartElementConditionalMethods(List<ConditionalMethod> conditionalMethodsList){
		startElementMethodsList.addAll(conditionalMethodsList);
	}
	
	public void addEndElementConditionalMethods(List<ConditionalMethod> conditionalMethodsList){
		endElementMethodsList.addAll(conditionalMethodsList);
	}
	
	public  void addStartDocumentMethods(List<Method> methodsList){
		startDocumentMethodsList.addAll(methodsList);
	}
	
	public void executeStartDocumentMethods(Object targetObject){
		executeMethodsWithoutParameters(targetObject,startDocumentMethodsList);
	}

	public void addEndDocumentMethods(List<Method> methodsList) {
		if(methodsList.size()>0)
			endDocumentMethodsList.addAll(methodsList);
		
	}
	
	public void executeEndDocumentMethods(Object targetObject){
		executeMethodsWithoutParameters(targetObject,endDocumentMethodsList);
	}
	
	public void executeStartElementMethods(Object targetObject,ContextVariables contextVariables) throws SAXException{
		executeConditionalMethods(targetObject,contextVariables,startElementMethodsList);
	}
	
	public void executeEndElementMethods(Object targetObject,ContextVariables contextVariables) throws SAXException{
		executeConditionalMethods(targetObject,contextVariables,endElementMethodsList);
	}

	private void executeMethodsWithoutParameters(Object targetObject,List<Method> methodsList) {
		for(Method method:methodsList){
			if(method.getParameterTypes().length==0){
				try {
					method.invoke(targetObject, new Object[]{});
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.getCause().printStackTrace();
				}
			}
				
		}
	}
	
	private void executeConditionalMethods(Object targetObject,ContextVariables contextVariables, List<ConditionalMethod> conditionalMethodsList) throws SAXException{
		for(ConditionalMethod cm: conditionalMethodsList){
			if(cm.verify(contextVariables)){
				try {
					Method method=cm.getMethod();
					if(method.getParameterTypes().length==0){
						method.invoke(targetObject, new Object[]{});
					}
					else{
						Object parameters[]=new Object[method.getParameterTypes().length];
						for(int i=0;i<parameters.length;++i){
							parameters[i]=buildParameter(method.getParameterTypes()[i],method.getParameterAnnotations()[i],contextVariables);
						}
						method.invoke(targetObject, parameters);
						
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					throw new SAXException(e);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					throw new SAXException(e);
				} catch (InvocationTargetException e) {
					e.getCause().printStackTrace();
					throw new SAXException(e);
				}catch(Exception e){
					e.getCause().printStackTrace();
					throw new SAXException(e);
				}
			}
		}
	}

	private Object buildParameter(Class<?> parameterClass, Annotation[] parameterAnnotations, ContextVariables contextVariables) {
		if(parameterClass.equals(ContextVariables.class))
			return contextVariables;
		else if(parameterAnnotations.length>0){
			for(Annotation annotation: parameterAnnotations){
				if(annotation instanceof Attribute)
					return buildParameterBasedOnAttribute(parameterClass,(Attribute)annotation,contextVariables);
				else if(annotation instanceof AttributeMap)
					return buildAttributeMap(contextVariables);
				else if(annotation instanceof CurrentBranch)
					return contextVariables.getCurrentBranch();
				else if(annotation instanceof Tag)
					return contextVariables.getLastEvent().getTag();
				else if(annotation instanceof Uri)
					return contextVariables.getLastEvent().getUri();
				else if(annotation instanceof LocalName)
					return contextVariables.getLastEvent().getLocalName();
				else if (annotation instanceof GeneralUseMap)
					return contextVariables.getGeneralUseMap();
				else if(annotation instanceof Body){
					Body body=(Body) annotation;
					if(body.tab()&&body.newLine())
						return contextVariables.getBody();
					else if(body.tab())
						return contextVariables.getBody().replaceAll("\n*", "");
					else if(body.newLine())
						return contextVariables.getBody().replaceAll("\t*", "");
					else 
						return contextVariables.getBody().replaceAll("[\n\t]*", "");
				}
			}
		}
		
		return null;
	}

	private Map<String,String> buildAttributeMap(ContextVariables contextVariables) {
		if(contextVariables.getLastEvent()!=null&&contextVariables.getLastEvent().getAtributesHolder()!=null)
			return contextVariables.getLastEvent().getAtributesHolder().getAttributeMap();
		return null;
	}

	private Object buildParameterBasedOnAttribute(Class<?> parameterClass,
			Attribute attribute, ContextVariables contextVariables) {
		
		String	value=contextVariables.getLastEvent().getAtributesHolder().getValue(attribute.value());
		
		if(parameterClass.equals(Integer.TYPE))
			return buildInt(value);
		else if(parameterClass.equals(Integer.class))
			return buildInteger(value);
		else if(parameterClass.equals(Boolean.TYPE))
			return buildPrimitiveBoolean(value);
		else if(parameterClass.equals(Boolean.class))
			return buildBoolean(value);
		else if(parameterClass.equals(Long.TYPE))
			return buildPrimitiveLong(value);
		else if(parameterClass.equals(Long.class))
			return buildLong(value);
		else if(parameterClass.equals(Float.TYPE))
			return buildPrimitiveFloat(value);
		else if(parameterClass.equals(Float.class))
			return buildFloat(value);
		else if(parameterClass.equals(Double.TYPE))
			return buildPrimitiveDouble(value);
		else if(parameterClass.equals(Double.class))
			return buildDouble(value);
		else if(parameterClass.equals(Short.TYPE))
			return buildPrimitiveShort(value);
		else if(parameterClass.equals(Short.class))
			return buildShort(value);
		else if(parameterClass.equals(Byte.TYPE))
			return buildPrimitiveByte(value);
		else if(parameterClass.equals(Byte.class))
			return buildByte(value);
		else if(parameterClass.equals(Character.TYPE))
			return buildChar(value);
		else if(parameterClass.equals(Character.class))
			return buildCharacter(value);
		else if(parameterClass.equals(String.class))
			return value;
		
		return null;
	}

	private Character buildCharacter(String value) {
		if(value!=null&&value.length()==1){
			return value.charAt(0);
		}
		else
			return null;
	}

	private char buildChar(String value) {
		if(value!=null&&value.length()==1){
			return value.charAt(0);
		}
		else
			return Character.MIN_VALUE;
	}

	private Byte buildByte(String value) {
		if(value!=null){
			return Byte.parseByte(value);
		}
		else
			return null;
	}

	private byte buildPrimitiveByte(String value) {
		if(value!=null){
			return Byte.parseByte(value);
		}
		else
			return Byte.MIN_VALUE;
	}

	private Short buildShort(String value) {
		if(value!=null){
			return Short.parseShort(value);
		}
		else
			return null;
	}

	private short buildPrimitiveShort(String value) {
		if(value!=null){
			return Short.parseShort(value);
		}
		else
			return Short.MIN_VALUE;
	}

	private Double buildDouble(String value) {
		if(value!=null){
			return Double.parseDouble(value);
		}
		else
			return null;
	}

	private double buildPrimitiveDouble(String value) {
		if(value!=null){
			return Double.parseDouble(value);
		}
		else
			return Double.MIN_VALUE;
	}

	private Float buildFloat(String value) {
		if(value!=null){
			return Float.parseFloat(value);
		}
		else
			return null;
	}

	private float buildPrimitiveFloat(String value) {
		if(value!=null){
			return Float.parseFloat(value);
		}
		else
			return Float.MIN_VALUE;
	}

	private Long buildLong(String value) {
		if(value!=null){
			return Long.parseLong(value);
		}
		else
			return null;
	}

	private long buildPrimitiveLong(String value) {
		if(value!=null){
			return Long.parseLong(value);
		}
		else
			return Long.MIN_VALUE;
	}

	private boolean buildPrimitiveBoolean(String value) {
		if(value!=null){
			return Boolean.parseBoolean(value);
		}
		else
			return false;
	}
	
	private Boolean buildBoolean(String value) {
		if(value!=null){
			return Boolean.parseBoolean(value);
		}
		else
			return null;
	}

	private Integer buildInteger(String value) {
		
		if(value!=null){
			return Integer.parseInt(value);
		}
		else
			return null;
	}

	private int buildInt(String value) {
		
		if(value!=null)
			return Integer.parseInt(value);
		else
			return Integer.MIN_VALUE;
	}
	
	

	

}
