package edu.northeastern.cs5200.models;

import javax.el.MethodNotFoundException;

public class HeadingWidget extends Widget {
  public HeadingWidget() {
    super();
  }

  public HeadingWidget(int id, String name, int width, int height, String cssClass, String cssStyle,
                       String text, int order, int pageId) {
    super(id, name, width, height, cssClass, cssStyle, text, order, pageId,
            2, null, null, null, true, true, WidgetType.HEADING);
  }

  public HeadingWidget(int id, String name, int width, int height, String cssClass, String cssStyle,
                       String text, int order, int pageId, int size) {
    super(id, name, width, height, cssClass, cssStyle, text, order, pageId,
            size, null, null, null, true, true, WidgetType.HEADING);
  }
}
