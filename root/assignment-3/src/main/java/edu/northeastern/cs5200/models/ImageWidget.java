package edu.northeastern.cs5200.models;

import javax.el.MethodNotFoundException;

public class ImageWidget extends Widget {
  public ImageWidget() {
    super();
  }

  public ImageWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order, int pageId, String src) {
    super(id, name, width, height, cssClass, cssStyle, text, order, pageId,
            0, null, src, null, true, true, WidgetType.IMAGE);
  }
}
