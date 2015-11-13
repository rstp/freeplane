package org.freeplane.core.ui.components.html;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

import org.freeplane.core.util.Convertible;
import org.freeplane.core.util.Quantity;
import org.junit.Before;
import org.junit.Test;

public class CssRuleBuilderShould {
	private CssRuleBuilder cssRuleBuilder;
	@Before
	public void setup() {
		cssRuleBuilder = new CssRuleBuilder();
	}
	private void assertRule(String ruleContent) {
		String rule = cssRuleBuilder.buildRule();
		assertEquals(ruleContent, rule);
	}

	@Test
	public void returnEmptyRule() throws Exception {
		assertRule("");
	}

	@Test
	public void ignoreNullFont() throws Exception {
		cssRuleBuilder.withFont(null);
		assertRule("");
	}

	@Test
	public void addFont() throws Exception {
		cssRuleBuilder.withFont(new Font("arial", Font.BOLD | Font.ITALIC, 10));
		assertRule(" font-family: Arial;  font-size: 10pt; font-weight: bold; font-style: italic;");
	}


	@Test
	public void addFontScaleSize() throws Exception {
		final float fontScaleFactor = 2f;
		cssRuleBuilder.withFont(new Font("arial", Font.BOLD | Font.ITALIC, 10), fontScaleFactor);
		assertRule(" font-family: Arial;  font-size: 5pt; font-weight: bold; font-style: italic;");
	}


	@Test
	public void ignoreNullColor() throws Exception {
		cssRuleBuilder.withColor(null);
		assertRule("");
	}

	@Test
	public void addColor() throws Exception {
		cssRuleBuilder.withColor(Color.WHITE);
		assertRule("color: #ffffff;");
	}

	@Test
	public void ignoreNullBackgroundColor() throws Exception {
		cssRuleBuilder.withBackground(null);
		assertRule("");
	}

	@Test
	public void addBackgroundColor() throws Exception {
		cssRuleBuilder.withBackground(Color.WHITE);
		assertRule("background-color: #ffffff;");
	}


	@Test
	public void addCenterAlignment() throws Exception {
		cssRuleBuilder.withAlignment(SwingConstants.CENTER);
		assertRule("text-align: center;");
	}

	@Test
	public void addLeftAlignment() throws Exception {
		cssRuleBuilder.withAlignment(SwingConstants.LEFT);
		assertRule("text-align: left;");
	}

	@Test
	public void addRightAlignment() throws Exception {
		cssRuleBuilder.withAlignment(SwingConstants.RIGHT);
		assertRule("text-align: right;");
	}

	enum Metrics implements Convertible{
		m(1d), cm(0.01d);

		Metrics(double factor){
			this.factor = factor;
		}

		final private double factor;
		@Override
		public double factor() {
			return factor;
		}
	}

	@Test
	public void addWidth() throws Exception {
		cssRuleBuilder.withWidthAsPt(new Quantity<Metrics>(1000, Metrics.cm));
		assertRule("width: 10pt;");
	}

	@Test
	public void ignoreNullWidth() throws Exception {
		cssRuleBuilder.withWidthAsPt(null);
		assertRule("");
	}
}
