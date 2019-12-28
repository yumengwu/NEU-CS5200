package edu.northeastern.cs5200.models;

public class Widget {
  private int id;
  private String name;
  private int width;
  private int height;
  private String cssClass;
  private String cssStyle;
  private String text;
  private int order;
  private int pageId;
  private int size;
  private String html;
  private String src;
  private String url;
  private boolean shareable;
  private boolean expandable;
  private WidgetType dtype;

  public Widget() {
  }

  public Widget(int id, String name, int width, int height, String cssClass, String cssStyle,
                String text, int order, int pageId) {
    this.id = id;
    this.name = name;
    this.width = width;
    this.height = height;
    this.cssClass = cssClass;
    this.cssStyle = cssStyle;
    this.text = text;
    this.order = order;
    this.pageId = pageId;
  }

  public Widget(int id, String name, int width, int height, String cssClass, String cssStyle,
                String text, int order, int pageId, int size, String html, String src, String url,
                boolean shareable, boolean expandable, WidgetType dtype) {
    this.id = id;
    this.name = name;
    this.width = width;
    this.height = height;
    this.cssClass = cssClass;
    this.cssStyle = cssStyle;
    this.text = text;
    this.order = order;
    this.pageId = pageId;
    this.size = size;
    this.html = html;
    this.src = src;
    this.url = url;
    this.shareable = shareable;
    this.expandable = expandable;
    this.dtype = dtype;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getCssClass() {
    return cssClass;
  }

  public void setCssClass(String cssClass) {
    this.cssClass = cssClass;
  }

  public String getCssStyle() {
    return cssStyle;
  }

  public void setCssStyle(String cssStyle) {
    this.cssStyle = cssStyle;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public int getPageId() {
    return pageId;
  }

  public void setPageId(int pageId) {
    this.pageId = pageId;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }

  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean getShareable() {
    return shareable;
  }

  public void setShareable(boolean shareable) {
    this.shareable = shareable;
  }

  public boolean getExpandable() {
    return expandable;
  }

  public void setExpandable(boolean expandable) {
    this.expandable = expandable;
  }

  public WidgetType getDtype() {
    return dtype;
  }

  public void setDtype(WidgetType dtype) {
    this.dtype = dtype;
  }

  @Override
  public String toString() {
    String res = "id: " + this.getId() + ", type: " + this.getDtype() + ", name: " + this.getName()
            + ", width: " + this.getWidth() + ", height: " + this.getHeight() + ", cssClass: "
            + this.getCssClass() + ", cssStyle: " + this.getCssStyle() + ", text: " + this.getText()
            + ", order: " + this.getOrder();
    switch (this.getDtype()) {
      case HEADING:
        res += ", size: " + this.getSize();
        break;
      case HTML:
        res += ", html: " + this.getHtml();
        break;
      case IMAGE:
        res += ", src: " + this.getSrc();
        break;
      case YOUTUBE:
        res += ", url: " + this.getUrl() + ", shareable: " + this.getShareable() + ", expandable: "
                + this.getExpandable();
        break;
    }
    return res;
  }
}
