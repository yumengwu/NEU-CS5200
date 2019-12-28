package edu.northeastern.cs5200;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.northeastern.cs5200.daos.DeveloperDao;
import edu.northeastern.cs5200.daos.DeveloperDaoImpl;
import edu.northeastern.cs5200.daos.PageDao;
import edu.northeastern.cs5200.daos.PageDaoImpl;
import edu.northeastern.cs5200.daos.RoleDao;
import edu.northeastern.cs5200.daos.RoleDaoImpl;
import edu.northeastern.cs5200.daos.UserDao;
import edu.northeastern.cs5200.daos.UserDaoImpl;
import edu.northeastern.cs5200.daos.WebsiteDao;
import edu.northeastern.cs5200.daos.WebsiteDaoImpl;
import edu.northeastern.cs5200.daos.WidgetDao;
import edu.northeastern.cs5200.daos.WidgetDaoImpl;
import edu.northeastern.cs5200.maps.RoleIdMap;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class hw_jdbc_wu_yumeng {
  public static void main(String[] args) {
    Connection.closeConnection();
    // Create the following developers and users. Insert into the correct tables
    // depending on the type

    List<Phone> alicePhone = new ArrayList<>();
    alicePhone.add(new Phone("123-234-3456", true));
    alicePhone.add(new Phone("234-345-4566", false));

    List<Address> aliceAddress = new ArrayList<>();
    aliceAddress.add(new Address("123 Adam St.", "Alton", null, "01234", true));
    aliceAddress.add(new Address("234 Birch St.", "Boston", null, "02345", false));

    List<Phone> bobPhone = new ArrayList<>();
    bobPhone.add(new Phone("345-456-5677", true));

    List<Address> bobAddress = new ArrayList<>();
    bobAddress.add(new Address("345 Charles St.", "Chelms", null, "03455", true));
    bobAddress.add(new Address("456 Down St.", "Dalton", null, "04566", false));
    bobAddress.add(new Address("543 East St.", "Everett", null, "01112", false));

    List<Phone> charliePhone = new ArrayList<>();
    charliePhone.add(new Phone("321-432-5435", true));
    charliePhone.add(new Phone("432-432-5433", false));
    charliePhone.add(new Phone("543-543-6544", false));

    List<Address> charlieAddress = new ArrayList<>();
    charlieAddress.add(new Address("654 Frank St.", "Foulton", null, "04322", true));

    DeveloperDao developerDao = DeveloperDaoImpl.getInstance();
    developerDao.createDeveloper(
            new Developer("4321rewq", 12, "Alice", "Wonder",
                    "alice", "alice", "alice@wonder.com", null,
                    alicePhone, aliceAddress));
    developerDao.createDeveloper(
            new Developer("5432trew", 23, "Bob", "Marley",
                    "bob", "bob", "bob@marley.com", null,
                    bobPhone, bobAddress));
    developerDao.createDeveloper(
            new Developer("6543ytre", 34, "Charles", "Garcia",
                    "charlie", "charlie", "chuch@garcia.com", null,
                    charliePhone, charlieAddress));
    UserDao userDao = UserDaoImpl.getInstance();
    userDao.createUser(
            new User(45, "Dan", "Martin", "dan", "dan",
                    "dan@martin.com", null, null, null, "7654fda", true));
    userDao.createUser(
            new User(56, "Ed", "Karaz", "ed", "ed",
                    "ed@kar.com", null, null, null, "5678dfgh", true));

    // Select all the developers and users
    System.out.println("Select all the developers:");
    Collection<Developer> developers = developerDao.findAllDevelopers();
    for (Developer developer : developers) {
      System.out.println(developer.toString());
    }
    System.out.println();
    System.out.println("Select all the users:");
    Collection<User> users = userDao.findAllUsers();
    for (User user : users) {
      System.out.println(user.toString());
    }

    // Create the following web sites for the developers above. For both the created
    // field and updated field, use the date your assignment will be graded, e.g., do
    // not hardcode it

    int aliceDevId = developerDao.findDeveloperByUsername("alice").getId();
    int bobDevId = developerDao.findDeveloperByUsername("bob").getId();
    int charlieDevId = developerDao.findDeveloperByUsername("charlie").getId();
    WebsiteDao websiteDao = WebsiteDaoImpl.getInstance();
    websiteDao.createWebsiteForDeveloper(aliceDevId,
            new Website(123, "Facebook",
                    "an online social media and social networking service",
                    new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
                    new SimpleDateFormat("YYYY-MM-dd").format(new Date()), 1234234));
    websiteDao.createWebsiteForDeveloper(bobDevId,
            new Website(234, "Twitter",
                    "an online news and social networking service",
                    new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
                    new SimpleDateFormat("YYYY-MM-dd").format(new Date()), 4321543));
    websiteDao.createWebsiteForDeveloper(charlieDevId,
            new Website(345, "Wikipedia",
                    "a free online encyclopedia",
                    new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
                    new SimpleDateFormat("YYYY-MM-dd").format(new Date()), 3456654));
    websiteDao.createWebsiteForDeveloper(aliceDevId,
            new Website(456, "CNN",
                    "an American basic cable and satellite television news channel",
                    new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
                    new SimpleDateFormat("YYYY-MM-dd").format(new Date()), 6543345));
    websiteDao.createWebsiteForDeveloper(bobDevId,
            new Website(567, "CNET",
                    "an American media website that publishes reviews, news, articles, " +
                            "blogs, podcasts and videos on technology and consumer electronics",
                    new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
                    new SimpleDateFormat("YYYY-MM-dd").format(new Date()), 5433455));
    websiteDao.createWebsiteForDeveloper(charlieDevId,
            new Website(678, "Gizmodo",
                    "a design, technology, science and science fiction website that " +
                            "also writes articles on politics",
                    new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
                    new SimpleDateFormat("YYYY-MM-dd").format(new Date()), 4322345));
    Map<String, Integer> websiteNameMap = new HashMap<>();
    System.out.println();
    System.out.println("Select all the websites:");
    Collection<Website> websites = websiteDao.findAllWebsites();
    for (Website website : websites) {
      websiteNameMap.put(website.getName(), website.getId());
      System.out.println(website.toString());
    }

    // create website role
    RoleIdMap roleIdMap = RoleIdMap.getInstance();

    RoleDao roleDao = RoleDaoImpl.getInstance();

    roleDao.assignWebsiteRole(aliceDevId, websiteNameMap.get("Facebook"), roleIdMap.getRoleId("owner"));
    roleDao.assignWebsiteRole(bobDevId, websiteNameMap.get("Facebook"), roleIdMap.getRoleId("editor"));
    roleDao.assignWebsiteRole(charlieDevId, websiteNameMap.get("Facebook"), roleIdMap.getRoleId("admin"));

    roleDao.assignWebsiteRole(bobDevId, websiteNameMap.get("Twitter"), roleIdMap.getRoleId("owner"));
    roleDao.assignWebsiteRole(charlieDevId, websiteNameMap.get("Twitter"), roleIdMap.getRoleId("editor"));
    roleDao.assignWebsiteRole(aliceDevId, websiteNameMap.get("Twitter"), roleIdMap.getRoleId("admin"));

    roleDao.assignWebsiteRole(charlieDevId, websiteNameMap.get("Wikipedia"), roleIdMap.getRoleId("owner"));
    roleDao.assignWebsiteRole(aliceDevId, websiteNameMap.get("Wikipedia"), roleIdMap.getRoleId("editor"));
    roleDao.assignWebsiteRole(bobDevId, websiteNameMap.get("Wikipedia"), roleIdMap.getRoleId("admin"));

    roleDao.assignWebsiteRole(aliceDevId, websiteNameMap.get("CNN"), roleIdMap.getRoleId("owner"));
    roleDao.assignWebsiteRole(bobDevId, websiteNameMap.get("CNN"), roleIdMap.getRoleId("editor"));
    roleDao.assignWebsiteRole(charlieDevId, websiteNameMap.get("CNN"), roleIdMap.getRoleId("admin"));

    roleDao.assignWebsiteRole(bobDevId, websiteNameMap.get("CNET"), roleIdMap.getRoleId("owner"));
    roleDao.assignWebsiteRole(charlieDevId, websiteNameMap.get("CNET"), roleIdMap.getRoleId("editor"));
    roleDao.assignWebsiteRole(aliceDevId, websiteNameMap.get("CNET"), roleIdMap.getRoleId("admin"));

    roleDao.assignWebsiteRole(charlieDevId, websiteNameMap.get("Gizmodo"), roleIdMap.getRoleId("owner"));
    roleDao.assignWebsiteRole(aliceDevId, websiteNameMap.get("Gizmodo"), roleIdMap.getRoleId("editor"));
    roleDao.assignWebsiteRole(bobDevId, websiteNameMap.get("Gizmodo"), roleIdMap.getRoleId("admin"));

    // Create the following pages for the web sites above. Use the semester's start date for
    // the created field. Use the assignment's due date for the updated field.
    PageDao pageDao = PageDaoImpl.getInstance();
    pageDao.createPageForWebsite(websiteNameMap.get("CNET"), new Page(123, "Home",
            "Landing page",
            new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
            new SimpleDateFormat("YYYY-MM-dd").format(new Date()), 123434));
    pageDao.createPageForWebsite(websiteNameMap.get("Gizmodo"), new Page(234, "About",
            "Website description",
            new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
            new SimpleDateFormat("YYYY-MM-dd").format(new Date()), 234545));
    pageDao.createPageForWebsite(websiteNameMap.get("Wikipedia"), new Page(345, "Contact",
            "Addresses, phones, and contact info",
            new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
            new SimpleDateFormat("YYYY-MM-dd").format(new Date()), 345656));
    pageDao.createPageForWebsite(websiteNameMap.get("CNN"), new Page(456, "Preferences",
            "Where users can configure their preferences",
            new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
            new SimpleDateFormat("YYYY-MM-dd").format(new Date()), 456776));
    pageDao.createPageForWebsite(websiteNameMap.get("CNET"), new Page(567, "Profile",
            "Users can configure their personal information",
            new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
            new SimpleDateFormat("YYYY-MM-dd").format(new Date()), 567878));

    // Select all the pages
    System.out.println();
    System.out.println("Select all the pages");
    Map<String, Integer> pageNameMap = new HashMap<>();
    Collection<Page> pages = pageDao.findAllPages();
    for (Page page : pages) {
      pageNameMap.put(page.getTitle(), page.getId());
      System.out.println(page.toString());
    }

    // create page role
    roleDao.assignPageRole(aliceDevId, pageNameMap.get("Home"), roleIdMap.getRoleId("editor"));
    roleDao.assignPageRole(bobDevId, pageNameMap.get("Home"), roleIdMap.getRoleId("reviewer"));
    roleDao.assignPageRole(charlieDevId, pageNameMap.get("Home"), roleIdMap.getRoleId("writer"));

    roleDao.assignPageRole(bobDevId, pageNameMap.get("About"), roleIdMap.getRoleId("editor"));
    roleDao.assignPageRole(charlieDevId, pageNameMap.get("About"), roleIdMap.getRoleId("reviewer"));
    roleDao.assignPageRole(aliceDevId, pageNameMap.get("About"), roleIdMap.getRoleId("writer"));

    roleDao.assignPageRole(charlieDevId, pageNameMap.get("Contact"), roleIdMap.getRoleId("editor"));
    roleDao.assignPageRole(aliceDevId, pageNameMap.get("Contact"), roleIdMap.getRoleId("reviewer"));
    roleDao.assignPageRole(bobDevId, pageNameMap.get("Contact"), roleIdMap.getRoleId("writer"));

    roleDao.assignPageRole(aliceDevId, pageNameMap.get("Preferences"), roleIdMap.getRoleId("editor"));
    roleDao.assignPageRole(bobDevId, pageNameMap.get("Preferences"), roleIdMap.getRoleId("reviewer"));
    roleDao.assignPageRole(charlieDevId, pageNameMap.get("Preferences"), roleIdMap.getRoleId("writer"));

    roleDao.assignPageRole(bobDevId, pageNameMap.get("Profile"), roleIdMap.getRoleId("editor"));
    roleDao.assignPageRole(charlieDevId, pageNameMap.get("Profile"), roleIdMap.getRoleId("reviewer"));
    roleDao.assignPageRole(aliceDevId, pageNameMap.get("Profile"), roleIdMap.getRoleId("writer"));

    WidgetDao widgetDao = WidgetDaoImpl.getInstance();
    widgetDao.createWidgetForPage(pageNameMap.get("Home"),
            new HeadingWidget(0, "head123", 0, 0, null, null,
                    "Welcome", 0, pageNameMap.get("Home")));
    widgetDao.createWidgetForPage(pageNameMap.get("About"),
            new HtmlWidget(1, "post234", 0, 0, null, null,
                    "<p>Lorem</p>", 0, pageNameMap.get("About"), ""));
    widgetDao.createWidgetForPage(pageNameMap.get("Contact"),
            new HeadingWidget(2, "head345", 0, 0, null, null,
                    "Hi", 1, pageNameMap.get("Contact")));
    widgetDao.createWidgetForPage(pageNameMap.get("Contact"),
            new HtmlWidget(3, "intro456", 0, 0, null, null,
                    "<h1>Hi</h1>", 2, pageNameMap.get("Contact"), null));
    widgetDao.createWidgetForPage(pageNameMap.get("Contact"),
            new ImageWidget(4, "image345", 50, 100, null, null,
                    null, 3, pageNameMap.get("Contact"), "/img/567.png"));
    widgetDao.createWidgetForPage(pageNameMap.get("Preferences"),
            new YouTubeWidget(5, "video456", 400, 300, null, null,
                    null, 0, pageNameMap.get("Preferences"), "https://youtu.be/h67VX51QXiQ", true, true));

    System.out.println();
    System.out.println("Select all the widgets:");
    Collection<Widget> widgets = widgetDao.findAllWidgets();
    for (Widget widget : widgets) {
      System.out.println(widget.toString());
    }

    // Implement Updates
    // Update developer - Update Charlie's primary phone number to 333-444-5555
    Developer charlie = developerDao.findDeveloperByUsername("charlie");
    for (int i = 0; i < charlie.getPhones().size(); ++i) {
      if (charlie.getPhones().get(i).getPrimary()) {
        charlie.getPhones().get(i).setNumber("333-444-5555");
        break;
      }
    }
    developerDao.updateDeveloper(charlieDevId, charlie);

    // select charlie's phones
    System.out.println();
    System.out.println("Charlie's phone:");
    charlie = developerDao.findDeveloperByUsername("charlie");
    for (Phone phone : charlie.getPhones()) {
      System.out.println(phone.toString());
    }

    // Update widget - Update the relative order of widget head345 on the page so that it's new order is 3.
    // Note that the other widget's order needs to update as well
    Collection<Widget> widgets1 = widgetDao.findWidgetsForPage(pageNameMap.get("Contact"));
    boolean needUpdateWidget = false;
    for (Widget widget : widgets1) {
      if (widget.getName().equals("head345") && widget.getOrder() != 3) {
        widget.setOrder(3);
        needUpdateWidget = true;
        widgetDao.updateWidget(widget.getId(), widget);
        break;
      }
    }
    if (needUpdateWidget) {
      for (Widget widget : widgets1) {
        if (!widget.getName().equals("head345") && widget.getOrder() <= 3) {
          widget.setOrder(widget.getOrder() - 1);
          widgetDao.updateWidget(widget.getId(), widget);
        }
      }
    }

    // widgets in Contact page
    System.out.println();
    System.out.println("widgets in contact page:");
    widgets1 = widgetDao.findWidgetsForPage(pageNameMap.get("Contact"));
    for (Widget widget : widgets1) {
      System.out.println(widget.toString());
    }

    // Update page - Append 'CNET - ' to the beginning of all CNET's page titles
    Collection<Page> pages1 = pageDao.findPagesForWebsite(websiteNameMap.get("CNET"));
    for (Page page : pages1) {
      if (page.getTitle().indexOf("CNET - ") != 0) {
        page.setTitle("CNET - " + page.getTitle());
        pageDao.updatePage(page.getId(), page);
      }
    }

    // pages in CNET page
    System.out.println();
    System.out.println("pages in CNET page");
    pages1 = pageDao.findPagesForWebsite(websiteNameMap.get("CNET"));
    for (Page page : pages1) {
      System.out.println(page.toString());
    }

    // Update roles - Swap Charlie's and Bob's role in CNET's Home page
    int charlieRoleId = roleDao.getWebsiteRoleByPersonId(websiteNameMap.get("CNET"), charlieDevId);
    int bobRoleId = roleDao.getWebsiteRoleByPersonId(websiteNameMap.get("CNET"), bobDevId);
    roleDao.deleteWebsiteRole(charlieDevId, websiteNameMap.get("CNET"), charlieRoleId);
    roleDao.deleteWebsiteRole(bobDevId, websiteNameMap.get("CNET"), bobRoleId);
    roleDao.assignWebsiteRole(charlieDevId, websiteNameMap.get("CNET"), bobRoleId);
    roleDao.assignWebsiteRole(bobDevId, websiteNameMap.get("CNET"), charlieRoleId);

    // Implement Deletes
    // Delete developer - Delete Alice's primary address
    Developer alice = developerDao.findDeveloperByUsername("alice");
    Address deleteAddress = null;
    for (Address address : alice.getAddresses()) {
      if (address.isPrimary()) {
        deleteAddress = address;
        break;
      }
    }
    alice.getAddresses().remove(deleteAddress);
    developerDao.updateDeveloper(alice.getId(), alice);

    System.out.println();
    System.out.println("alice's address:");
    alice = developerDao.findDeveloperByUsername("alice");
    for (Address address : alice.getAddresses()) {
      System.out.println(address.toString());
    }

    // Delete widget - Remove the last widget in the Contact page. The last widget is the one with the highest
    // value in the order field
    widgets1 = widgetDao.findWidgetsForPage(pageNameMap.get("Contact"));
    Widget deleteWidget = null;
    for (Widget widget : widgets1) {
      if (deleteWidget == null || widget.getOrder() > deleteWidget.getOrder()) {
        deleteWidget = widget;
      }
    }
    widgetDao.deleteWidget(deleteWidget.getId());

    // select widgets in Contact page
    System.out.println();
    System.out.println("select widgets in Contact page:");
    widgets1 = widgetDao.findWidgetsForPage(pageNameMap.get("Contact"));
    for (Widget widget : widgets1) {
      System.out.println(widget.toString());
    }

    // Delete page - Remove the last updated page in Wikipedia
    pages1 = pageDao.findPagesForWebsite(websiteNameMap.get("Wikipedia"));
    Page deletePage = null;
    Date lastDate = null;
    Date tempDate;
    for (Page page : pages1) {
      if (deletePage == null) {
        deletePage = page;
      }
      try {
        tempDate = new SimpleDateFormat("yyyy-mm-dd").parse(page.getUpdated());
        if (lastDate == null) {
          lastDate = tempDate;
        }
      }
      catch (ParseException err) {
        throw new RuntimeException();
      }
      if (tempDate.after(lastDate)) {
        lastDate = tempDate;
        deletePage = page;
      }
    }
    pageDao.deletePage(deletePage.getId());

    System.out.println();
    System.out.println("select pages of Wikipedia");
    pages1 = pageDao.findPagesForWebsite(websiteNameMap.get("Wikipedia"));
    for (Page page : pages1) {
      System.out.println(page.toString());
    }

    // Delete website - Remove the CNET web site, as well as all related roles and privileges relating
    // developers to the Website and Pages
    websiteDao.deleteWebsite(websiteNameMap.get("CNET"));

    System.out.println();
    System.out.println("current websites:");
    websites = websiteDao.findAllWebsites();
    for (Website website : websites) {
      System.out.println(website.toString());
    }
  }
}
