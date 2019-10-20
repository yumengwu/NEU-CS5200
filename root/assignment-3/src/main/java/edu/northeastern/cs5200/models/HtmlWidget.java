package edu.northeastern.cs5200.models;

import javax.el.MethodNotFoundException;

public class HtmlWidget extends Widget {
  public HtmlWidget() {
    super();
  }

  public HtmlWidget(int id, String name, int width, int height, String cssClass, String cssStyle,
                    String text, int order, int pageId, String html) {
    super(id, name, width, height, cssClass, cssStyle, text, order, pageId,
            0, html, null, null, true, true, WidgetType.HTML);
  }
}
