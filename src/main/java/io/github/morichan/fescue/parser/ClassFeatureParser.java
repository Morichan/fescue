// Generated from ClassFeature.g4 by ANTLR 4.7.1
package io.github.morichan.fescue.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ClassFeatureParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, READONLY=17, 
		UNION=18, SUBSETS=19, REDEFINES=20, ORDERED=21, UNIQUE=22, QUERY=23, IN=24, 
		OUT=25, INOUT=26, RETURN=27, PUBLIC=28, PRIBATE=29, PROTECTED=30, PACKAGE=31, 
		UNLIMITATION=32, RANGE=33, NEW=34, BOOLEAN=35, INTEGER=36, STRING=37, 
		UNLIMITED_NATURAL=38, VOID=39, LPAREN=40, RPAREN=41, LBRACE=42, RBRACE=43, 
		LBRACK=44, RBRACK=45, SQUOT=46, DQUOT=47, COMMA=48, DOT=49, ASSIGN=50, 
		COLON=51, SLASH=52, DECIMAL_LITERAL=53, HEX_LITERAL=54, OCT_LITERAL=55, 
		BINARY_LITERAL=56, INTEGER_LITERAL=57, FLOAT_LITERAL=58, HEX_FLOAT_LITERAL=59, 
		BOOL_LITERAL=60, TRUL_LITERAL=61, FALSE_LITERAL=62, NULL_LITERAL=63, NULL=64, 
		NUL=65, NIL=66, NONE=67, UNDEF=68, WS=69, SQUOT_LITERAL=70, DQUOT_LITERAL=71, 
		IDENTIFIER=72;
	public static final int
		RULE_property = 0, RULE_operation = 1, RULE_visibility = 2, RULE_divided = 3, 
		RULE_name = 4, RULE_propType = 5, RULE_type = 6, RULE_multiplicityRange = 7, 
		RULE_lower = 8, RULE_upper = 9, RULE_valueSpecification = 10, RULE_defaultValue = 11, 
		RULE_propModifiers = 12, RULE_properties = 13, RULE_propModifier = 14, 
		RULE_propertyName = 15, RULE_parameterList = 16, RULE_parameter = 17, 
		RULE_direction = 18, RULE_parameterName = 19, RULE_typeExpression = 20, 
		RULE_paramProperties = 21, RULE_returnType = 22, RULE_operProperties = 23, 
		RULE_operProperty = 24, RULE_operName = 25, RULE_expression = 26, RULE_creator = 27, 
		RULE_createdName = 28, RULE_classCreatorRest = 29, RULE_explicitGenericInvocationSuffix = 30, 
		RULE_arguments = 31, RULE_expressionList = 32, RULE_primitiveType = 33, 
		RULE_literal = 34, RULE_integerLiteral = 35, RULE_floatLiteral = 36;
	public static final String[] ruleNames = {
		"property", "operation", "visibility", "divided", "name", "propType", 
		"type", "multiplicityRange", "lower", "upper", "valueSpecification", "defaultValue", 
		"propModifiers", "properties", "propModifier", "propertyName", "parameterList", 
		"parameter", "direction", "parameterName", "typeExpression", "paramProperties", 
		"returnType", "operProperties", "operProperty", "operName", "expression", 
		"creator", "createdName", "classCreatorRest", "explicitGenericInvocationSuffix", 
		"arguments", "expressionList", "primitiveType", "literal", "integerLiteral", 
		"floatLiteral"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'!'", "'not'", "'NOT'", "'%'", "'<='", "'>='", "'>'", "'<'", "'=='", 
		"'!='", "'&&'", "'and'", "'AND'", "'||'", "'or'", "'OR'", "'readOnly'", 
		"'union'", "'subsets'", "'redefines'", "'ordered'", "'unique'", "'query'", 
		"'in'", "'out'", "'inout'", "'return'", "'+'", "'-'", "'#'", "'~'", "'*'", 
		"'..'", "'new'", "'Boolean'", "'Integer'", "'String'", "'UnlimitedNatural'", 
		"'void'", "'('", "')'", "'{'", "'}'", "'['", "']'", "'''", "'\"'", "','", 
		"'.'", "'='", "':'", "'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "READONLY", "UNION", "SUBSETS", "REDEFINES", 
		"ORDERED", "UNIQUE", "QUERY", "IN", "OUT", "INOUT", "RETURN", "PUBLIC", 
		"PRIBATE", "PROTECTED", "PACKAGE", "UNLIMITATION", "RANGE", "NEW", "BOOLEAN", 
		"INTEGER", "STRING", "UNLIMITED_NATURAL", "VOID", "LPAREN", "RPAREN", 
		"LBRACE", "RBRACE", "LBRACK", "RBRACK", "SQUOT", "DQUOT", "COMMA", "DOT", 
		"ASSIGN", "COLON", "SLASH", "DECIMAL_LITERAL", "HEX_LITERAL", "OCT_LITERAL", 
		"BINARY_LITERAL", "INTEGER_LITERAL", "FLOAT_LITERAL", "HEX_FLOAT_LITERAL", 
		"BOOL_LITERAL", "TRUL_LITERAL", "FALSE_LITERAL", "NULL_LITERAL", "NULL", 
		"NUL", "NIL", "NONE", "UNDEF", "WS", "SQUOT_LITERAL", "DQUOT_LITERAL", 
		"IDENTIFIER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ClassFeature.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ClassFeatureParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class PropertyContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public VisibilityContext visibility() {
			return getRuleContext(VisibilityContext.class,0);
		}
		public DividedContext divided() {
			return getRuleContext(DividedContext.class,0);
		}
		public PropTypeContext propType() {
			return getRuleContext(PropTypeContext.class,0);
		}
		public MultiplicityRangeContext multiplicityRange() {
			return getRuleContext(MultiplicityRangeContext.class,0);
		}
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public PropModifiersContext propModifiers() {
			return getRuleContext(PropModifiersContext.class,0);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitProperty(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_property);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUBLIC) | (1L << PRIBATE) | (1L << PROTECTED) | (1L << PACKAGE))) != 0)) {
				{
				setState(74);
				visibility();
				}
			}

			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SLASH) {
				{
				setState(77);
				divided();
				}
			}

			setState(80);
			name();
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(81);
				propType();
				}
			}

			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(84);
				multiplicityRange();
				}
			}

			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(87);
				defaultValue();
				}
			}

			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(90);
				propModifiers();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public VisibilityContext visibility() {
			return getRuleContext(VisibilityContext.class,0);
		}
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public OperPropertiesContext operProperties() {
			return getRuleContext(OperPropertiesContext.class,0);
		}
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitOperation(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUBLIC) | (1L << PRIBATE) | (1L << PROTECTED) | (1L << PACKAGE))) != 0)) {
				{
				setState(93);
				visibility();
				}
			}

			setState(96);
			name();
			setState(97);
			parameterList();
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(98);
				returnType();
				}
			}

			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(101);
				operProperties();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VisibilityContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(ClassFeatureParser.PUBLIC, 0); }
		public TerminalNode PRIBATE() { return getToken(ClassFeatureParser.PRIBATE, 0); }
		public TerminalNode PROTECTED() { return getToken(ClassFeatureParser.PROTECTED, 0); }
		public TerminalNode PACKAGE() { return getToken(ClassFeatureParser.PACKAGE, 0); }
		public VisibilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_visibility; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterVisibility(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitVisibility(this);
		}
	}

	public final VisibilityContext visibility() throws RecognitionException {
		VisibilityContext _localctx = new VisibilityContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_visibility);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUBLIC) | (1L << PRIBATE) | (1L << PROTECTED) | (1L << PACKAGE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DividedContext extends ParserRuleContext {
		public TerminalNode SLASH() { return getToken(ClassFeatureParser.SLASH, 0); }
		public DividedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_divided; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterDivided(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitDivided(this);
		}
	}

	public final DividedContext divided() throws RecognitionException {
		DividedContext _localctx = new DividedContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_divided);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(SLASH);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ClassFeatureParser.IDENTIFIER, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropTypeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public PropTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterPropType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitPropType(this);
		}
	}

	public final PropTypeContext propType() throws RecognitionException {
		PropTypeContext _localctx = new PropTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_propType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(ClassFeatureParser.COLON, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ClassFeatureParser.IDENTIFIER, 0); }
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(COLON);
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(113);
				match(IDENTIFIER);
				}
				break;
			case BOOLEAN:
			case INTEGER:
			case STRING:
			case UNLIMITED_NATURAL:
				{
				setState(114);
				primitiveType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicityRangeContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(ClassFeatureParser.LBRACK, 0); }
		public UpperContext upper() {
			return getRuleContext(UpperContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(ClassFeatureParser.RBRACK, 0); }
		public LowerContext lower() {
			return getRuleContext(LowerContext.class,0);
		}
		public TerminalNode RANGE() { return getToken(ClassFeatureParser.RANGE, 0); }
		public MultiplicityRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicityRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterMultiplicityRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitMultiplicityRange(this);
		}
	}

	public final MultiplicityRangeContext multiplicityRange() throws RecognitionException {
		MultiplicityRangeContext _localctx = new MultiplicityRangeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_multiplicityRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(LBRACK);
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(118);
				lower();
				setState(119);
				match(RANGE);
				}
				break;
			}
			setState(123);
			upper();
			setState(124);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LowerContext extends ParserRuleContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public ValueSpecificationContext valueSpecification() {
			return getRuleContext(ValueSpecificationContext.class,0);
		}
		public LowerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lower; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterLower(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitLower(this);
		}
	}

	public final LowerContext lower() throws RecognitionException {
		LowerContext _localctx = new LowerContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_lower);
		try {
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				integerLiteral();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				valueSpecification();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpperContext extends ParserRuleContext {
		public TerminalNode UNLIMITATION() { return getToken(ClassFeatureParser.UNLIMITATION, 0); }
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public ValueSpecificationContext valueSpecification() {
			return getRuleContext(ValueSpecificationContext.class,0);
		}
		public UpperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_upper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterUpper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitUpper(this);
		}
	}

	public final UpperContext upper() throws RecognitionException {
		UpperContext _localctx = new UpperContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_upper);
		try {
			setState(133);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case UNLIMITATION:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				match(UNLIMITATION);
				}
				break;
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				integerLiteral();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(132);
				valueSpecification();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueSpecificationContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ClassFeatureParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ClassFeatureParser.RPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ClassFeatureParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ClassFeatureParser.COMMA, i);
		}
		public ValueSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueSpecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterValueSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitValueSpecification(this);
		}
	}

	public final ValueSpecificationContext valueSpecification() throws RecognitionException {
		ValueSpecificationContext _localctx = new ValueSpecificationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_valueSpecification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(LPAREN);
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << PUBLIC) | (1L << PRIBATE) | (1L << NEW) | (1L << LPAREN) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << NULL_LITERAL))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (SQUOT_LITERAL - 70)) | (1L << (DQUOT_LITERAL - 70)) | (1L << (IDENTIFIER - 70)))) != 0)) {
				{
				{
				setState(136);
				expression(0);
				}
				}
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(142);
				match(COMMA);
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << PUBLIC) | (1L << PRIBATE) | (1L << NEW) | (1L << LPAREN) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << NULL_LITERAL))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (SQUOT_LITERAL - 70)) | (1L << (DQUOT_LITERAL - 70)) | (1L << (IDENTIFIER - 70)))) != 0)) {
					{
					{
					setState(143);
					expression(0);
					}
					}
					setState(148);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(154);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultValueContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(ClassFeatureParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitDefaultValue(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(ASSIGN);
			setState(157);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropModifiersContext extends ParserRuleContext {
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public PropModifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propModifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterPropModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitPropModifiers(this);
		}
	}

	public final PropModifiersContext propModifiers() throws RecognitionException {
		PropModifiersContext _localctx = new PropModifiersContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_propModifiers);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			properties();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertiesContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ClassFeatureParser.LBRACE, 0); }
		public List<PropModifierContext> propModifier() {
			return getRuleContexts(PropModifierContext.class);
		}
		public PropModifierContext propModifier(int i) {
			return getRuleContext(PropModifierContext.class,i);
		}
		public TerminalNode RBRACE() { return getToken(ClassFeatureParser.RBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ClassFeatureParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ClassFeatureParser.COMMA, i);
		}
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitProperties(this);
		}
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_properties);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(LBRACE);
			setState(162);
			propModifier();
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(163);
				match(COMMA);
				setState(164);
				propModifier();
				}
				}
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(170);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropModifierContext extends ParserRuleContext {
		public TerminalNode READONLY() { return getToken(ClassFeatureParser.READONLY, 0); }
		public TerminalNode UNION() { return getToken(ClassFeatureParser.UNION, 0); }
		public TerminalNode SUBSETS() { return getToken(ClassFeatureParser.SUBSETS, 0); }
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public TerminalNode REDEFINES() { return getToken(ClassFeatureParser.REDEFINES, 0); }
		public TerminalNode ORDERED() { return getToken(ClassFeatureParser.ORDERED, 0); }
		public TerminalNode UNIQUE() { return getToken(ClassFeatureParser.UNIQUE, 0); }
		public PropModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterPropModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitPropModifier(this);
		}
	}

	public final PropModifierContext propModifier() throws RecognitionException {
		PropModifierContext _localctx = new PropModifierContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_propModifier);
		try {
			setState(180);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case READONLY:
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				match(READONLY);
				}
				break;
			case UNION:
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
				match(UNION);
				}
				break;
			case SUBSETS:
				enterOuterAlt(_localctx, 3);
				{
				setState(174);
				match(SUBSETS);
				setState(175);
				propertyName();
				}
				break;
			case REDEFINES:
				enterOuterAlt(_localctx, 4);
				{
				setState(176);
				match(REDEFINES);
				setState(177);
				propertyName();
				}
				break;
			case ORDERED:
				enterOuterAlt(_localctx, 5);
				{
				setState(178);
				match(ORDERED);
				}
				break;
			case UNIQUE:
				enterOuterAlt(_localctx, 6);
				{
				setState(179);
				match(UNIQUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyNameContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PropertyNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterPropertyName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitPropertyName(this);
		}
	}

	public final PropertyNameContext propertyName() throws RecognitionException {
		PropertyNameContext _localctx = new PropertyNameContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_propertyName);
		try {
			setState(184);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ClassFeatureParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ClassFeatureParser.RPAREN, 0); }
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ClassFeatureParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ClassFeatureParser.COMMA, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitParameterList(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(LPAREN);
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << IN) | (1L << OUT) | (1L << INOUT) | (1L << RETURN) | (1L << PUBLIC) | (1L << PRIBATE) | (1L << NEW) | (1L << LPAREN) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << NULL_LITERAL))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (SQUOT_LITERAL - 70)) | (1L << (DQUOT_LITERAL - 70)) | (1L << (IDENTIFIER - 70)))) != 0)) {
				{
				{
				setState(187);
				parameter();
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(188);
					match(COMMA);
					setState(189);
					parameter();
					}
					}
					setState(194);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(200);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public ParameterNameContext parameterName() {
			return getRuleContext(ParameterNameContext.class,0);
		}
		public TypeExpressionContext typeExpression() {
			return getRuleContext(TypeExpressionContext.class,0);
		}
		public DirectionContext direction() {
			return getRuleContext(DirectionContext.class,0);
		}
		public MultiplicityRangeContext multiplicityRange() {
			return getRuleContext(MultiplicityRangeContext.class,0);
		}
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public ParamPropertiesContext paramProperties() {
			return getRuleContext(ParamPropertiesContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IN) | (1L << OUT) | (1L << INOUT) | (1L << RETURN))) != 0)) {
				{
				setState(202);
				direction();
				}
			}

			setState(205);
			parameterName();
			setState(206);
			typeExpression();
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(207);
				multiplicityRange();
				}
			}

			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(210);
				defaultValue();
				}
			}

			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(213);
				paramProperties();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectionContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(ClassFeatureParser.IN, 0); }
		public TerminalNode OUT() { return getToken(ClassFeatureParser.OUT, 0); }
		public TerminalNode INOUT() { return getToken(ClassFeatureParser.INOUT, 0); }
		public TerminalNode RETURN() { return getToken(ClassFeatureParser.RETURN, 0); }
		public DirectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_direction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterDirection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitDirection(this);
		}
	}

	public final DirectionContext direction() throws RecognitionException {
		DirectionContext _localctx = new DirectionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_direction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IN) | (1L << OUT) | (1L << INOUT) | (1L << RETURN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterNameContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParameterNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterParameterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitParameterName(this);
		}
	}

	public final ParameterNameContext parameterName() throws RecognitionException {
		ParameterNameContext _localctx = new ParameterNameContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_parameterName);
		try {
			setState(220);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeExpressionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterTypeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitTypeExpression(this);
		}
	}

	public final TypeExpressionContext typeExpression() throws RecognitionException {
		TypeExpressionContext _localctx = new TypeExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_typeExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamPropertiesContext extends ParserRuleContext {
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public ParamPropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramProperties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterParamProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitParamProperties(this);
		}
	}

	public final ParamPropertiesContext paramProperties() throws RecognitionException {
		ParamPropertiesContext _localctx = new ParamPropertiesContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_paramProperties);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			properties();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnTypeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode COLON() { return getToken(ClassFeatureParser.COLON, 0); }
		public TerminalNode VOID() { return getToken(ClassFeatureParser.VOID, 0); }
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitReturnType(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_returnType);
		try {
			setState(229);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(227);
				match(COLON);
				setState(228);
				match(VOID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperPropertiesContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ClassFeatureParser.LBRACE, 0); }
		public List<OperPropertyContext> operProperty() {
			return getRuleContexts(OperPropertyContext.class);
		}
		public OperPropertyContext operProperty(int i) {
			return getRuleContext(OperPropertyContext.class,i);
		}
		public TerminalNode RBRACE() { return getToken(ClassFeatureParser.RBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ClassFeatureParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ClassFeatureParser.COMMA, i);
		}
		public OperPropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operProperties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterOperProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitOperProperties(this);
		}
	}

	public final OperPropertiesContext operProperties() throws RecognitionException {
		OperPropertiesContext _localctx = new OperPropertiesContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_operProperties);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(LBRACE);
			setState(232);
			operProperty();
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(233);
				match(COMMA);
				setState(234);
				operProperty();
				}
				}
				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(240);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperPropertyContext extends ParserRuleContext {
		public TerminalNode REDEFINES() { return getToken(ClassFeatureParser.REDEFINES, 0); }
		public OperNameContext operName() {
			return getRuleContext(OperNameContext.class,0);
		}
		public TerminalNode QUERY() { return getToken(ClassFeatureParser.QUERY, 0); }
		public TerminalNode ORDERED() { return getToken(ClassFeatureParser.ORDERED, 0); }
		public TerminalNode UNIQUE() { return getToken(ClassFeatureParser.UNIQUE, 0); }
		public OperPropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operProperty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterOperProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitOperProperty(this);
		}
	}

	public final OperPropertyContext operProperty() throws RecognitionException {
		OperPropertyContext _localctx = new OperPropertyContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_operProperty);
		try {
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REDEFINES:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				match(REDEFINES);
				setState(243);
				operName();
				}
				break;
			case QUERY:
				enterOuterAlt(_localctx, 2);
				{
				setState(244);
				match(QUERY);
				}
				break;
			case ORDERED:
				enterOuterAlt(_localctx, 3);
				{
				setState(245);
				match(ORDERED);
				}
				break;
			case UNIQUE:
				enterOuterAlt(_localctx, 4);
				{
				setState(246);
				match(UNIQUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperNameContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OperNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterOperName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitOperName(this);
		}
	}

	public final OperNameContext operName() throws RecognitionException {
		OperNameContext _localctx = new OperNameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_operName);
		try {
			setState(251);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(250);
				expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Token bop;
		public TerminalNode LPAREN() { return getToken(ClassFeatureParser.LPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(ClassFeatureParser.RPAREN, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(ClassFeatureParser.IDENTIFIER, 0); }
		public TerminalNode NEW() { return getToken(ClassFeatureParser.NEW, 0); }
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() {
			return getRuleContext(ExplicitGenericInvocationSuffixContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, 52, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				setState(254);
				match(LPAREN);
				setState(255);
				expression(0);
				setState(256);
				match(RPAREN);
				}
				break;
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
			case BOOL_LITERAL:
			case NULL_LITERAL:
			case SQUOT_LITERAL:
			case DQUOT_LITERAL:
				{
				setState(258);
				literal();
				}
				break;
			case IDENTIFIER:
				{
				setState(259);
				match(IDENTIFIER);
				}
				break;
			case NEW:
				{
				setState(260);
				match(NEW);
				setState(261);
				creator();
				}
				break;
			case PUBLIC:
			case PRIBATE:
				{
				setState(262);
				((ExpressionContext)_localctx).bop = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==PUBLIC || _la==PRIBATE) ) {
					((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(263);
				expression(8);
				}
				break;
			case T__0:
			case T__1:
			case T__2:
				{
				setState(264);
				((ExpressionContext)_localctx).bop = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2))) != 0)) ) {
					((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(265);
				expression(7);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(296);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(294);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(268);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(269);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << UNLIMITATION) | (1L << SLASH))) != 0)) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(270);
						expression(7);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(271);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(272);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PUBLIC || _la==PRIBATE) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(273);
						expression(6);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(274);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(275);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(276);
						expression(5);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(277);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(278);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__8 || _la==T__9) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(279);
						expression(4);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(280);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(281);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(282);
						expression(3);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(283);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(284);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__14) | (1L << T__15))) != 0)) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(285);
						expression(2);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(286);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(287);
						((ExpressionContext)_localctx).bop = match(DOT);
						setState(290);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
						case 1:
							{
							setState(288);
							match(IDENTIFIER);
							}
							break;
						case 2:
							{
							setState(289);
							explicitGenericInvocationSuffix();
							}
							break;
						}
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(292);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(293);
						arguments();
						}
						break;
					}
					} 
				}
				setState(298);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CreatorContext extends ParserRuleContext {
		public CreatedNameContext createdName() {
			return getRuleContext(CreatedNameContext.class,0);
		}
		public ClassCreatorRestContext classCreatorRest() {
			return getRuleContext(ClassCreatorRestContext.class,0);
		}
		public CreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitCreator(this);
		}
	}

	public final CreatorContext creator() throws RecognitionException {
		CreatorContext _localctx = new CreatorContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_creator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			createdName();
			setState(300);
			classCreatorRest();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatedNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(ClassFeatureParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(ClassFeatureParser.IDENTIFIER, i);
		}
		public CreatedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createdName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterCreatedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitCreatedName(this);
		}
	}

	public final CreatedNameContext createdName() throws RecognitionException {
		CreatedNameContext _localctx = new CreatedNameContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_createdName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(IDENTIFIER);
			setState(307);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(303);
				match(DOT);
				setState(304);
				match(IDENTIFIER);
				}
				}
				setState(309);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassCreatorRestContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ClassCreatorRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classCreatorRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterClassCreatorRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitClassCreatorRest(this);
		}
	}

	public final ClassCreatorRestContext classCreatorRest() throws RecognitionException {
		ClassCreatorRestContext _localctx = new ClassCreatorRestContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_classCreatorRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			arguments();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExplicitGenericInvocationSuffixContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ClassFeatureParser.IDENTIFIER, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitGenericInvocationSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterExplicitGenericInvocationSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitExplicitGenericInvocationSuffix(this);
		}
	}

	public final ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() throws RecognitionException {
		ExplicitGenericInvocationSuffixContext _localctx = new ExplicitGenericInvocationSuffixContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_explicitGenericInvocationSuffix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(IDENTIFIER);
			setState(313);
			arguments();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ClassFeatureParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ClassFeatureParser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitArguments(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			match(LPAREN);
			setState(317);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << PUBLIC) | (1L << PRIBATE) | (1L << NEW) | (1L << LPAREN) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << NULL_LITERAL))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (SQUOT_LITERAL - 70)) | (1L << (DQUOT_LITERAL - 70)) | (1L << (IDENTIFIER - 70)))) != 0)) {
				{
				setState(316);
				expressionList();
				}
			}

			setState(319);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitExpressionList(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			expression(0);
			setState(326);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(322);
				match(COMMA);
				setState(323);
				expression(0);
				}
				}
				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimitiveTypeContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(ClassFeatureParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(ClassFeatureParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(ClassFeatureParser.STRING, 0); }
		public TerminalNode UNLIMITED_NATURAL() { return getToken(ClassFeatureParser.UNLIMITED_NATURAL, 0); }
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitPrimitiveType(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_primitiveType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << INTEGER) | (1L << STRING) | (1L << UNLIMITED_NATURAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public FloatLiteralContext floatLiteral() {
			return getRuleContext(FloatLiteralContext.class,0);
		}
		public TerminalNode SQUOT_LITERAL() { return getToken(ClassFeatureParser.SQUOT_LITERAL, 0); }
		public TerminalNode DQUOT_LITERAL() { return getToken(ClassFeatureParser.DQUOT_LITERAL, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(ClassFeatureParser.BOOL_LITERAL, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(ClassFeatureParser.NULL_LITERAL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_literal);
		try {
			setState(337);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(331);
				integerLiteral();
				}
				break;
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(332);
				floatLiteral();
				}
				break;
			case SQUOT_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(333);
				match(SQUOT_LITERAL);
				}
				break;
			case DQUOT_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(334);
				match(DQUOT_LITERAL);
				}
				break;
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(335);
				match(BOOL_LITERAL);
				}
				break;
			case NULL_LITERAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(336);
				match(NULL_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLiteralContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(ClassFeatureParser.DECIMAL_LITERAL, 0); }
		public TerminalNode HEX_LITERAL() { return getToken(ClassFeatureParser.HEX_LITERAL, 0); }
		public TerminalNode OCT_LITERAL() { return getToken(ClassFeatureParser.OCT_LITERAL, 0); }
		public TerminalNode BINARY_LITERAL() { return getToken(ClassFeatureParser.BINARY_LITERAL, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitIntegerLiteral(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_integerLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FloatLiteralContext extends ParserRuleContext {
		public TerminalNode FLOAT_LITERAL() { return getToken(ClassFeatureParser.FLOAT_LITERAL, 0); }
		public TerminalNode HEX_FLOAT_LITERAL() { return getToken(ClassFeatureParser.HEX_FLOAT_LITERAL, 0); }
		public FloatLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).enterFloatLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClassFeatureListener ) ((ClassFeatureListener)listener).exitFloatLiteral(this);
		}
	}

	public final FloatLiteralContext floatLiteral() throws RecognitionException {
		FloatLiteralContext _localctx = new FloatLiteralContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_floatLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			_la = _input.LA(1);
			if ( !(_la==FLOAT_LITERAL || _la==HEX_FLOAT_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 26:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 1);
		case 6:
			return precpred(_ctx, 11);
		case 7:
			return precpred(_ctx, 10);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3J\u015a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\5\2N\n\2\3\2\5\2Q\n\2\3\2\3\2"+
		"\5\2U\n\2\3\2\5\2X\n\2\3\2\5\2[\n\2\3\2\5\2^\n\2\3\3\5\3a\n\3\3\3\3\3"+
		"\3\3\5\3f\n\3\3\3\5\3i\n\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b"+
		"\5\bv\n\b\3\t\3\t\3\t\3\t\5\t|\n\t\3\t\3\t\3\t\3\n\3\n\5\n\u0083\n\n\3"+
		"\13\3\13\3\13\5\13\u0088\n\13\3\f\3\f\7\f\u008c\n\f\f\f\16\f\u008f\13"+
		"\f\3\f\3\f\7\f\u0093\n\f\f\f\16\f\u0096\13\f\7\f\u0098\n\f\f\f\16\f\u009b"+
		"\13\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\7\17\u00a8\n\17"+
		"\f\17\16\17\u00ab\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\5\20\u00b7\n\20\3\21\3\21\5\21\u00bb\n\21\3\22\3\22\3\22\3\22\7\22"+
		"\u00c1\n\22\f\22\16\22\u00c4\13\22\7\22\u00c6\n\22\f\22\16\22\u00c9\13"+
		"\22\3\22\3\22\3\23\5\23\u00ce\n\23\3\23\3\23\3\23\5\23\u00d3\n\23\3\23"+
		"\5\23\u00d6\n\23\3\23\5\23\u00d9\n\23\3\24\3\24\3\25\3\25\5\25\u00df\n"+
		"\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\5\30\u00e8\n\30\3\31\3\31\3\31"+
		"\3\31\7\31\u00ee\n\31\f\31\16\31\u00f1\13\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\32\3\32\5\32\u00fa\n\32\3\33\3\33\5\33\u00fe\n\33\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u010d\n\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u0125\n\34\3\34\3\34\7\34\u0129"+
		"\n\34\f\34\16\34\u012c\13\34\3\35\3\35\3\35\3\36\3\36\3\36\7\36\u0134"+
		"\n\36\f\36\16\36\u0137\13\36\3\37\3\37\3 \3 \3 \3!\3!\5!\u0140\n!\3!\3"+
		"!\3\"\3\"\3\"\7\"\u0147\n\"\f\"\16\"\u014a\13\"\3#\3#\3$\3$\3$\3$\3$\3"+
		"$\5$\u0154\n$\3%\3%\3&\3&\3&\2\3\66\'\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJ\2\16\3\2\36!\3\2\32\35\3\2\36\37"+
		"\3\2\3\5\5\2\6\6\"\"\66\66\3\2\7\n\3\2\13\f\3\2\r\17\3\2\20\22\3\2%(\3"+
		"\2\67:\3\2<=\2\u016f\2M\3\2\2\2\4`\3\2\2\2\6j\3\2\2\2\bl\3\2\2\2\nn\3"+
		"\2\2\2\fp\3\2\2\2\16r\3\2\2\2\20w\3\2\2\2\22\u0082\3\2\2\2\24\u0087\3"+
		"\2\2\2\26\u0089\3\2\2\2\30\u009e\3\2\2\2\32\u00a1\3\2\2\2\34\u00a3\3\2"+
		"\2\2\36\u00b6\3\2\2\2 \u00ba\3\2\2\2\"\u00bc\3\2\2\2$\u00cd\3\2\2\2&\u00da"+
		"\3\2\2\2(\u00de\3\2\2\2*\u00e0\3\2\2\2,\u00e2\3\2\2\2.\u00e7\3\2\2\2\60"+
		"\u00e9\3\2\2\2\62\u00f9\3\2\2\2\64\u00fd\3\2\2\2\66\u010c\3\2\2\28\u012d"+
		"\3\2\2\2:\u0130\3\2\2\2<\u0138\3\2\2\2>\u013a\3\2\2\2@\u013d\3\2\2\2B"+
		"\u0143\3\2\2\2D\u014b\3\2\2\2F\u0153\3\2\2\2H\u0155\3\2\2\2J\u0157\3\2"+
		"\2\2LN\5\6\4\2ML\3\2\2\2MN\3\2\2\2NP\3\2\2\2OQ\5\b\5\2PO\3\2\2\2PQ\3\2"+
		"\2\2QR\3\2\2\2RT\5\n\6\2SU\5\f\7\2TS\3\2\2\2TU\3\2\2\2UW\3\2\2\2VX\5\20"+
		"\t\2WV\3\2\2\2WX\3\2\2\2XZ\3\2\2\2Y[\5\30\r\2ZY\3\2\2\2Z[\3\2\2\2[]\3"+
		"\2\2\2\\^\5\32\16\2]\\\3\2\2\2]^\3\2\2\2^\3\3\2\2\2_a\5\6\4\2`_\3\2\2"+
		"\2`a\3\2\2\2ab\3\2\2\2bc\5\n\6\2ce\5\"\22\2df\5.\30\2ed\3\2\2\2ef\3\2"+
		"\2\2fh\3\2\2\2gi\5\60\31\2hg\3\2\2\2hi\3\2\2\2i\5\3\2\2\2jk\t\2\2\2k\7"+
		"\3\2\2\2lm\7\66\2\2m\t\3\2\2\2no\7J\2\2o\13\3\2\2\2pq\5\16\b\2q\r\3\2"+
		"\2\2ru\7\65\2\2sv\7J\2\2tv\5D#\2us\3\2\2\2ut\3\2\2\2v\17\3\2\2\2w{\7."+
		"\2\2xy\5\22\n\2yz\7#\2\2z|\3\2\2\2{x\3\2\2\2{|\3\2\2\2|}\3\2\2\2}~\5\24"+
		"\13\2~\177\7/\2\2\177\21\3\2\2\2\u0080\u0083\5H%\2\u0081\u0083\5\26\f"+
		"\2\u0082\u0080\3\2\2\2\u0082\u0081\3\2\2\2\u0083\23\3\2\2\2\u0084\u0088"+
		"\7\"\2\2\u0085\u0088\5H%\2\u0086\u0088\5\26\f\2\u0087\u0084\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0087\u0086\3\2\2\2\u0088\25\3\2\2\2\u0089\u008d\7*\2\2"+
		"\u008a\u008c\5\66\34\2\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b"+
		"\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0099\3\2\2\2\u008f\u008d\3\2\2\2\u0090"+
		"\u0094\7\62\2\2\u0091\u0093\5\66\34\2\u0092\u0091\3\2\2\2\u0093\u0096"+
		"\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0098\3\2\2\2\u0096"+
		"\u0094\3\2\2\2\u0097\u0090\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2"+
		"\2\2\u0099\u009a\3\2\2\2\u009a\u009c\3\2\2\2\u009b\u0099\3\2\2\2\u009c"+
		"\u009d\7+\2\2\u009d\27\3\2\2\2\u009e\u009f\7\64\2\2\u009f\u00a0\5\66\34"+
		"\2\u00a0\31\3\2\2\2\u00a1\u00a2\5\34\17\2\u00a2\33\3\2\2\2\u00a3\u00a4"+
		"\7,\2\2\u00a4\u00a9\5\36\20\2\u00a5\u00a6\7\62\2\2\u00a6\u00a8\5\36\20"+
		"\2\u00a7\u00a5\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa"+
		"\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00ad\7-\2\2\u00ad"+
		"\35\3\2\2\2\u00ae\u00b7\7\23\2\2\u00af\u00b7\7\24\2\2\u00b0\u00b1\7\25"+
		"\2\2\u00b1\u00b7\5 \21\2\u00b2\u00b3\7\26\2\2\u00b3\u00b7\5 \21\2\u00b4"+
		"\u00b7\7\27\2\2\u00b5\u00b7\7\30\2\2\u00b6\u00ae\3\2\2\2\u00b6\u00af\3"+
		"\2\2\2\u00b6\u00b0\3\2\2\2\u00b6\u00b2\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6"+
		"\u00b5\3\2\2\2\u00b7\37\3\2\2\2\u00b8\u00bb\5\n\6\2\u00b9\u00bb\5\66\34"+
		"\2\u00ba\u00b8\3\2\2\2\u00ba\u00b9\3\2\2\2\u00bb!\3\2\2\2\u00bc\u00c7"+
		"\7*\2\2\u00bd\u00c2\5$\23\2\u00be\u00bf\7\62\2\2\u00bf\u00c1\5$\23\2\u00c0"+
		"\u00be\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2"+
		"\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00bd\3\2\2\2\u00c6"+
		"\u00c9\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00ca\3\2"+
		"\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00cb\7+\2\2\u00cb#\3\2\2\2\u00cc\u00ce"+
		"\5&\24\2\u00cd\u00cc\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf"+
		"\u00d0\5(\25\2\u00d0\u00d2\5*\26\2\u00d1\u00d3\5\20\t\2\u00d2\u00d1\3"+
		"\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d5\3\2\2\2\u00d4\u00d6\5\30\r\2\u00d5"+
		"\u00d4\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7\u00d9\5,"+
		"\27\2\u00d8\u00d7\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9%\3\2\2\2\u00da\u00db"+
		"\t\3\2\2\u00db\'\3\2\2\2\u00dc\u00df\5\n\6\2\u00dd\u00df\5\66\34\2\u00de"+
		"\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00df)\3\2\2\2\u00e0\u00e1\5\16\b\2"+
		"\u00e1+\3\2\2\2\u00e2\u00e3\5\34\17\2\u00e3-\3\2\2\2\u00e4\u00e8\5\16"+
		"\b\2\u00e5\u00e6\7\65\2\2\u00e6\u00e8\7)\2\2\u00e7\u00e4\3\2\2\2\u00e7"+
		"\u00e5\3\2\2\2\u00e8/\3\2\2\2\u00e9\u00ea\7,\2\2\u00ea\u00ef\5\62\32\2"+
		"\u00eb\u00ec\7\62\2\2\u00ec\u00ee\5\62\32\2\u00ed\u00eb\3\2\2\2\u00ee"+
		"\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f2\3\2"+
		"\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f3\7-\2\2\u00f3\61\3\2\2\2\u00f4\u00f5"+
		"\7\26\2\2\u00f5\u00fa\5\64\33\2\u00f6\u00fa\7\31\2\2\u00f7\u00fa\7\27"+
		"\2\2\u00f8\u00fa\7\30\2\2\u00f9\u00f4\3\2\2\2\u00f9\u00f6\3\2\2\2\u00f9"+
		"\u00f7\3\2\2\2\u00f9\u00f8\3\2\2\2\u00fa\63\3\2\2\2\u00fb\u00fe\5\n\6"+
		"\2\u00fc\u00fe\5\66\34\2\u00fd\u00fb\3\2\2\2\u00fd\u00fc\3\2\2\2\u00fe"+
		"\65\3\2\2\2\u00ff\u0100\b\34\1\2\u0100\u0101\7*\2\2\u0101\u0102\5\66\34"+
		"\2\u0102\u0103\7+\2\2\u0103\u010d\3\2\2\2\u0104\u010d\5F$\2\u0105\u010d"+
		"\7J\2\2\u0106\u0107\7$\2\2\u0107\u010d\58\35\2\u0108\u0109\t\4\2\2\u0109"+
		"\u010d\5\66\34\n\u010a\u010b\t\5\2\2\u010b\u010d\5\66\34\t\u010c\u00ff"+
		"\3\2\2\2\u010c\u0104\3\2\2\2\u010c\u0105\3\2\2\2\u010c\u0106\3\2\2\2\u010c"+
		"\u0108\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u012a\3\2\2\2\u010e\u010f\f\b"+
		"\2\2\u010f\u0110\t\6\2\2\u0110\u0129\5\66\34\t\u0111\u0112\f\7\2\2\u0112"+
		"\u0113\t\4\2\2\u0113\u0129\5\66\34\b\u0114\u0115\f\6\2\2\u0115\u0116\t"+
		"\7\2\2\u0116\u0129\5\66\34\7\u0117\u0118\f\5\2\2\u0118\u0119\t\b\2\2\u0119"+
		"\u0129\5\66\34\6\u011a\u011b\f\4\2\2\u011b\u011c\t\t\2\2\u011c\u0129\5"+
		"\66\34\5\u011d\u011e\f\3\2\2\u011e\u011f\t\n\2\2\u011f\u0129\5\66\34\4"+
		"\u0120\u0121\f\r\2\2\u0121\u0124\7\63\2\2\u0122\u0125\7J\2\2\u0123\u0125"+
		"\5> \2\u0124\u0122\3\2\2\2\u0124\u0123\3\2\2\2\u0125\u0129\3\2\2\2\u0126"+
		"\u0127\f\f\2\2\u0127\u0129\5@!\2\u0128\u010e\3\2\2\2\u0128\u0111\3\2\2"+
		"\2\u0128\u0114\3\2\2\2\u0128\u0117\3\2\2\2\u0128\u011a\3\2\2\2\u0128\u011d"+
		"\3\2\2\2\u0128\u0120\3\2\2\2\u0128\u0126\3\2\2\2\u0129\u012c\3\2\2\2\u012a"+
		"\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\67\3\2\2\2\u012c\u012a\3\2\2"+
		"\2\u012d\u012e\5:\36\2\u012e\u012f\5<\37\2\u012f9\3\2\2\2\u0130\u0135"+
		"\7J\2\2\u0131\u0132\7\63\2\2\u0132\u0134\7J\2\2\u0133\u0131\3\2\2\2\u0134"+
		"\u0137\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136;\3\2\2\2"+
		"\u0137\u0135\3\2\2\2\u0138\u0139\5@!\2\u0139=\3\2\2\2\u013a\u013b\7J\2"+
		"\2\u013b\u013c\5@!\2\u013c?\3\2\2\2\u013d\u013f\7*\2\2\u013e\u0140\5B"+
		"\"\2\u013f\u013e\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\3\2\2\2\u0141"+
		"\u0142\7+\2\2\u0142A\3\2\2\2\u0143\u0148\5\66\34\2\u0144\u0145\7\62\2"+
		"\2\u0145\u0147\5\66\34\2\u0146\u0144\3\2\2\2\u0147\u014a\3\2\2\2\u0148"+
		"\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149C\3\2\2\2\u014a\u0148\3\2\2\2"+
		"\u014b\u014c\t\13\2\2\u014cE\3\2\2\2\u014d\u0154\5H%\2\u014e\u0154\5J"+
		"&\2\u014f\u0154\7H\2\2\u0150\u0154\7I\2\2\u0151\u0154\7>\2\2\u0152\u0154"+
		"\7A\2\2\u0153\u014d\3\2\2\2\u0153\u014e\3\2\2\2\u0153\u014f\3\2\2\2\u0153"+
		"\u0150\3\2\2\2\u0153\u0151\3\2\2\2\u0153\u0152\3\2\2\2\u0154G\3\2\2\2"+
		"\u0155\u0156\t\f\2\2\u0156I\3\2\2\2\u0157\u0158\t\r\2\2\u0158K\3\2\2\2"+
		"(MPTWZ]`ehu{\u0082\u0087\u008d\u0094\u0099\u00a9\u00b6\u00ba\u00c2\u00c7"+
		"\u00cd\u00d2\u00d5\u00d8\u00de\u00e7\u00ef\u00f9\u00fd\u010c\u0124\u0128"+
		"\u012a\u0135\u013f\u0148\u0153";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}