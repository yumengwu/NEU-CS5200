package edu.northeastern.cs5200.models;

import javax.el.MethodNotFoundException;

public class YouTubeWidget extends Widget {
  public YouTubeWidget() {
    super();
  }

  public YouTubeWidget(int id, String name, int width, int height, String cssClass, String cssStyle,
                       String text, int order, int pageId, String url, boolean shareable, boolean expandable) {
    super(id, name, width, height, cssClass, cssStyle, text, order, pageId,
            0, null, null, url, shareable, expandable, WidgetType.YOUTUBE);
  }
}
