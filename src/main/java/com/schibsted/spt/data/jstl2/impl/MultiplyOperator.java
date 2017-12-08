
package com.schibsted.spt.data.jstl2.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.schibsted.spt.data.jstl2.JstlException;

public class MultiplyOperator extends NumericOperator {

  public MultiplyOperator(ExpressionNode left, ExpressionNode right) {
    super(left, right, "*");
  }

  public JsonNode perform(JsonNode v1, JsonNode v2) {
    if (v1.isTextual() || v2.isTextual()) {
      // if one operand is string: do string multiplication

      String str;
      int num;
      if (v1.isTextual() && !v2.isTextual()) {
        str = v1.asText();
        num = v2.intValue();
      } else if (v2.isTextual()) {
        str = v2.asText();
        num = v1.intValue();
      } else
        throw new JstlException("Can't multiply two strings!");

      StringBuilder buf = new StringBuilder();
      for ( ; num > 0; num--)
        buf.append(str);

      return new TextNode(buf.toString());
    } else
      // do numeric operation
      return super.perform(v1, v2);
  }

  protected double perform(double v1, double v2) {
    return v1 * v2;
  }

  protected long perform(long v1, long v2) {
    return v1 * v2;
  }
}
