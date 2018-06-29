// Generated from ClassFeature.g4 by ANTLR 4.7.1
package io.github.morichan.fescue.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ClassFeatureParser}.
 */
public interface ClassFeatureListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(ClassFeatureParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(ClassFeatureParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(ClassFeatureParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(ClassFeatureParser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#visibility}.
	 * @param ctx the parse tree
	 */
	void enterVisibility(ClassFeatureParser.VisibilityContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#visibility}.
	 * @param ctx the parse tree
	 */
	void exitVisibility(ClassFeatureParser.VisibilityContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#divided}.
	 * @param ctx the parse tree
	 */
	void enterDivided(ClassFeatureParser.DividedContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#divided}.
	 * @param ctx the parse tree
	 */
	void exitDivided(ClassFeatureParser.DividedContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(ClassFeatureParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(ClassFeatureParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#propType}.
	 * @param ctx the parse tree
	 */
	void enterPropType(ClassFeatureParser.PropTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#propType}.
	 * @param ctx the parse tree
	 */
	void exitPropType(ClassFeatureParser.PropTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ClassFeatureParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ClassFeatureParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#multiplicityRange}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicityRange(ClassFeatureParser.MultiplicityRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#multiplicityRange}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicityRange(ClassFeatureParser.MultiplicityRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#lower}.
	 * @param ctx the parse tree
	 */
	void enterLower(ClassFeatureParser.LowerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#lower}.
	 * @param ctx the parse tree
	 */
	void exitLower(ClassFeatureParser.LowerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#upper}.
	 * @param ctx the parse tree
	 */
	void enterUpper(ClassFeatureParser.UpperContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#upper}.
	 * @param ctx the parse tree
	 */
	void exitUpper(ClassFeatureParser.UpperContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#valueSpecification}.
	 * @param ctx the parse tree
	 */
	void enterValueSpecification(ClassFeatureParser.ValueSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#valueSpecification}.
	 * @param ctx the parse tree
	 */
	void exitValueSpecification(ClassFeatureParser.ValueSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(ClassFeatureParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(ClassFeatureParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#propModifiers}.
	 * @param ctx the parse tree
	 */
	void enterPropModifiers(ClassFeatureParser.PropModifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#propModifiers}.
	 * @param ctx the parse tree
	 */
	void exitPropModifiers(ClassFeatureParser.PropModifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(ClassFeatureParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(ClassFeatureParser.PropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#propModifier}.
	 * @param ctx the parse tree
	 */
	void enterPropModifier(ClassFeatureParser.PropModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#propModifier}.
	 * @param ctx the parse tree
	 */
	void exitPropModifier(ClassFeatureParser.PropModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#propertyName}.
	 * @param ctx the parse tree
	 */
	void enterPropertyName(ClassFeatureParser.PropertyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#propertyName}.
	 * @param ctx the parse tree
	 */
	void exitPropertyName(ClassFeatureParser.PropertyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(ClassFeatureParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(ClassFeatureParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(ClassFeatureParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(ClassFeatureParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#direction}.
	 * @param ctx the parse tree
	 */
	void enterDirection(ClassFeatureParser.DirectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#direction}.
	 * @param ctx the parse tree
	 */
	void exitDirection(ClassFeatureParser.DirectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#parameterName}.
	 * @param ctx the parse tree
	 */
	void enterParameterName(ClassFeatureParser.ParameterNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#parameterName}.
	 * @param ctx the parse tree
	 */
	void exitParameterName(ClassFeatureParser.ParameterNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#typeExpression}.
	 * @param ctx the parse tree
	 */
	void enterTypeExpression(ClassFeatureParser.TypeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#typeExpression}.
	 * @param ctx the parse tree
	 */
	void exitTypeExpression(ClassFeatureParser.TypeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#paramProperties}.
	 * @param ctx the parse tree
	 */
	void enterParamProperties(ClassFeatureParser.ParamPropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#paramProperties}.
	 * @param ctx the parse tree
	 */
	void exitParamProperties(ClassFeatureParser.ParamPropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(ClassFeatureParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(ClassFeatureParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#operProperties}.
	 * @param ctx the parse tree
	 */
	void enterOperProperties(ClassFeatureParser.OperPropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#operProperties}.
	 * @param ctx the parse tree
	 */
	void exitOperProperties(ClassFeatureParser.OperPropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#operProperty}.
	 * @param ctx the parse tree
	 */
	void enterOperProperty(ClassFeatureParser.OperPropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#operProperty}.
	 * @param ctx the parse tree
	 */
	void exitOperProperty(ClassFeatureParser.OperPropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#operName}.
	 * @param ctx the parse tree
	 */
	void enterOperName(ClassFeatureParser.OperNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#operName}.
	 * @param ctx the parse tree
	 */
	void exitOperName(ClassFeatureParser.OperNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ClassFeatureParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ClassFeatureParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterCreator(ClassFeatureParser.CreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitCreator(ClassFeatureParser.CreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#createdName}.
	 * @param ctx the parse tree
	 */
	void enterCreatedName(ClassFeatureParser.CreatedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#createdName}.
	 * @param ctx the parse tree
	 */
	void exitCreatedName(ClassFeatureParser.CreatedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterClassCreatorRest(ClassFeatureParser.ClassCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitClassCreatorRest(ClassFeatureParser.ClassCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#explicitGenericInvocationSuffix}.
	 * @param ctx the parse tree
	 */
	void enterExplicitGenericInvocationSuffix(ClassFeatureParser.ExplicitGenericInvocationSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#explicitGenericInvocationSuffix}.
	 * @param ctx the parse tree
	 */
	void exitExplicitGenericInvocationSuffix(ClassFeatureParser.ExplicitGenericInvocationSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(ClassFeatureParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(ClassFeatureParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(ClassFeatureParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(ClassFeatureParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(ClassFeatureParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(ClassFeatureParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ClassFeatureParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ClassFeatureParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(ClassFeatureParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(ClassFeatureParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClassFeatureParser#floatLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFloatLiteral(ClassFeatureParser.FloatLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClassFeatureParser#floatLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFloatLiteral(ClassFeatureParser.FloatLiteralContext ctx);
}