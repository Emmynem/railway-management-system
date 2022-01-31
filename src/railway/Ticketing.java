package railway;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Xnyder
 */
public class Ticketing extends JFrame {

    // All Frames
    private JFrame MainFrame, ViewFrame, EditFrame;
    // My TabbedPane
    private JTabbedPane Tabs;
    // All Panels Declaration
    private JPanel homeTab_jpa, bookTab_jpa, bookingsTab_jpa, addStationTab_jpa, stations_jpa, addTrainTab_jpa, trains_jpa, aboutTab_jpa;
    // Home Panels Declaration
    private JPanel count_trains_jpa, count_stations_jpa, count_bookings_jpa;
    // View Panel
    private JPanel view_jpa;
    // Edit Panel
    private JPanel edit_jpa;

    // Home Labels declaration
    private JLabel home_lbl, home_con_status_lbl, count_trains_lbl, icon_train_lbl, count_stations_lbl,
            icon_station_lbl, count_bookings_lbl, icon_bookings_lbl, dashboard_lbl;
    // Book Labels declaration
    private JLabel book_lbl, passenger_details_lbl, fname_lbl, lname_lbl, phoneNum_lbl, gender_lbl,
            booking_details_lbl, title_lbl, class_lbl, no_of_seats_lbl, from_station_lbl, to_station_lbl,
            departure_lbl, arrival_lbl, departure_hh_lbl, departure_mm_lbl, arrival_hh_lbl, arrival_mm_lbl,
            train_name_lbl, train_number_lbl, list_bookings_lbl, book_con_status_lbl;
    // Booking Labels declaration
    private JLabel bookings_lbl, bookings_con_status_lbl;
    // Add Station Labels declaration
    private JLabel add_station_lbl, add_station_details_lbl, add_station_name_lbl, add_station_type_lbl, add_station_con_status_lbl;
    // Stations Labels declaration
    private JLabel stations_lbl, stations_con_status_lbl;
    // Add Train Labels declaration
    private JLabel add_train_lbl, add_train_details_lbl, add_train_name_lbl, add_train_number_lbl, add_train_con_status_lbl;
    // Trains Labels declaration
    private JLabel trains_lbl, trains_con_status_lbl;
    // About Us Labels declaration
    private JLabel about_lbl, about_con_status_lbl, nigeria_flag_lbl, rivers_lbl, poweredby_lbl, emmynem_lbl;
    // View Labels declaration
    private JLabel view_name_lbl, view_phoneNum_lbl, view_phoneNum_value_lbl, view_gender_lbl, view_gender_value_lbl,
            view_title_lbl, view_title_value_lbl, view_class_lbl, view_class_value_lbl, view_no_of_seats_lbl, view_no_of_seats_value_lbl,
            view_from_station_lbl, view_from_station_value_lbl, view_to_station_lbl, view_to_station_value_lbl, view_departure_lbl,
            view_departure_value_lbl, view_arrival_lbl, view_arrival_value_lbl, view_train_name_lbl, view_train_name_value_lbl,
            view_train_number_lbl, view_train_number_value_lbl, view_date_lbl, view_t_no_lbl, view_seperator_line_lbl, view_seperator_line_2_lbl;

    // Book TextFields declaration
    private JTextField fname_txt, lname_txt, phoneNum_txt, no_of_seats_txt, departure_hh_txt, departure_mm_txt, arrival_hh_txt, arrival_mm_txt;
    // Edit Booking TextFields declaration
    private JTextField edit_fname_txt, edit_lname_txt, edit_phoneNum_txt, edit_no_of_seats_txt, edit_departure_hh_txt, edit_departure_mm_txt,
            edit_arrival_hh_txt, edit_arrival_mm_txt;
    // Add Station TextFields declaration
    private JTextField add_station_name_txt, add_station_type_txt;
    // Add Train TextFields declaration
    private JTextField add_train_name_txt, add_train_number_txt;
    // Bookings TextFields declaration
    private JTextField search_txt;

    // Book ComboBoxes declaration
    private JComboBox<String> gender_jcb, title_jcb, class_jcb, from_station_jcb, to_station_jcb,
            departure_am_pm_jcb, arrival_am_pm_jcb, train_name_jcb, train_number_jcb;
    // Edit Book ComboBoxes declaration
    private JComboBox<String> edit_gender_jcb, edit_title_jcb, edit_class_jcb, edit_from_station_jcb, edit_to_station_jcb,
            edit_departure_am_pm_jcb, edit_arrival_am_pm_jcb, edit_train_name_jcb, edit_train_number_jcb;
    // Bookings ComboBoxes declaration
    private JComboBox<String> filter_jcb;

    // Home Buttons declaration
    private JButton home_refresh_jbtn;
    // Book Buttons declaration
    private JButton submit_jbtn, save_jbtn, clear_jbtn;
    // Edit Book Buttons declaration
    private JButton edit_submit_jbtn;
    // Bookings Buttons declaration
    private JButton bookings_reload_jbtn, search_jbtn;
    // Add Station Buttons declaration
    private JButton create_station_jbtn;
    // Stations Buttons declaration
    private JButton stations_reload_jbtn;
    // Add Train Buttons declaration
    private JButton create_train_jbtn;
    // Trains Buttons declaration
    private JButton trains_reload_jbtn;

    // Book List declaration
    private JList book_jl;
    // Book DLM declaration
    private DefaultListModel dlm_book;
    // Bookings Table declaration
    private JTable bookings_tbl;
    // Bookings DTM declaration
    private DefaultTableModel dtm_bookings;
    // Stations Table declaration
    private JTable stations_tbl;
    // Stations DTM declaration
    private DefaultTableModel dtm_stations;
    // Trains Table declaration
    private JTable trains_tbl;
    // Trains DTM declaration
    private DefaultTableModel dtm_trains;
    // Book ScrollPane declaration
    private JScrollPane book_scroll;
    // Bookings ScrollPane declaration
    private JScrollPane bookings_scroll;
    // Stations ScrollPane declaration
    private JScrollPane stations_scroll;
    // Trains ScrollPane declaration
    private JScrollPane trains_scroll;

    // Menu bar
    private JMenuBar menubar_jmb;
    // Menus
    private JMenu connection_jmu;
    // MenuItem
    private JMenuItem refresh_jmi, connect_jmi;
    // Right Click PopUpMenu
    private JPopupMenu right_click_jmu;
    // Right Click PopUpMenu Items
    private JMenuItem right_click_edit_jmi, right_click_delete_jmi, right_click_view_jmi;

    // SQL Components
    private Connection con;
    private Statement stmt;
    private PreparedStatement stat;
    private ResultSet rs;

    // All objects
    private Object[] bookings_obj;
    private Object[] stations_obj;
    private Object[] trains_obj;

    // Book ArrayLists declaration
    private ArrayList<String> fnames = new ArrayList<>();
    private ArrayList<String> lnames = new ArrayList<>();
    private ArrayList<String> phoneNums = new ArrayList<>();
    private ArrayList<String> genders = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> classes = new ArrayList<>();
    private ArrayList<String> seats = new ArrayList<>();
    private ArrayList<String> from_stations = new ArrayList<>();
    private ArrayList<String> to_stations = new ArrayList<>();
    private ArrayList<String> departures = new ArrayList<>();
    private ArrayList<String> arrivals = new ArrayList<>();
    private ArrayList<String> train_names = new ArrayList<>();
    private ArrayList<String> train_numbers = new ArrayList<>();
    private ArrayList<Integer> t_nums = new ArrayList<>();

    // Sets for Book - Train Name Non duplicates
    private Set< String> trains = new HashSet<>();
    // Sets for Edit Book - Train Name Non duplicates
    private Set< String> edit_trains = new HashSet<>();

    // Used to check each booking details inputed and possibly print it out
    private HashSet<Booking> bookingList;

    // For ticket numbering
    private int num = 0;

    // Used to keep track of my database connection
    private boolean connectedToDB = false;

    // Used in saving edit chnages 
    private String global_tNum = "";

    public Ticketing() {

        MainFrame = new JFrame();

        // Tabbed pane
        Tabs = new JTabbedPane(JTabbedPane.TOP);

        // Making the Tab to be scrollable if there is more of the tabs and all of them cannot be contained
        Tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        //Collection Initialization
        bookingList = new HashSet<Booking>();

        allMenuComponents();
        allHomeComponents();
        allBookComponents();
        allBookingsComponents();
        allAddStationsComponents();
        allStationsComponents();
        allAddTrainsComponents();
        allTrainsComponents();
        allAboutUsComponents();
        allPopUpMenuComponents();

        MainFrame.add(Tabs);
        awaitingConn();

        MainFrame.setSize(1200, 800);
        MainFrame.setTitle("Railway Ticketing");
        MainFrame.setVisible(true);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setResizable(false);
        MainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/railway/images/Icon 3.PNG")));
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // Creating components
    public void addComponent() {
        MainFrame.add(Tabs);
        awaitingConn();

    }

    // All Menu Components & Attr
    private void allMenuComponents() {

        // Creating the MenuBar
        menubar_jmb = new JMenuBar();

        // Setting the MenuBar to the MainFrame
        MainFrame.setJMenuBar(menubar_jmb);

        // Adding New Menu (Connection)
        connection_jmu = new JMenu("Connection");
        connection_jmu.setMnemonic('c');

        // Adding Connection Menu to the MenuBar
        menubar_jmb.add(connection_jmu);

        // Adding Connection Menu Items
        connect_jmi = new JMenuItem("Connect");
        connection_jmu.add(connect_jmi);
        refresh_jmi = new JMenuItem("Refresh");
        connection_jmu.add(refresh_jmi);

        connect_jmi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        refresh_jmi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

    }

    // All PopUpMenu Components & attr
    private void allPopUpMenuComponents() {
        //  Creating the PopUpMenu
        right_click_jmu = new JPopupMenu("Options");

        // Adding Items to the PopUpMenu
        right_click_view_jmi = new JMenuItem("View");
        right_click_view_jmi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });
        right_click_jmu.add(right_click_view_jmi);
        right_click_jmu.addSeparator();

        right_click_edit_jmi = new JMenuItem("Edit");
        right_click_edit_jmi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        right_click_jmu.add(right_click_edit_jmi);

        right_click_delete_jmi = new JMenuItem("Delete");
        right_click_delete_jmi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        right_click_jmu.add(right_click_delete_jmi);
    }

    // All Home Components & Attr
    private void allHomeComponents() {

        // Home Panel initialization
        homeTab_jpa = new JPanel();
        homeTab_jpa.setBackground(new java.awt.Color(32, 38, 46));
        count_trains_jpa = new JPanel();
        count_trains_jpa.setBackground(new java.awt.Color(255, 255, 255));
        count_stations_jpa = new JPanel();
        count_stations_jpa.setBackground(new java.awt.Color(255, 255, 255));
        count_bookings_jpa = new JPanel();
        count_bookings_jpa.setBackground(new java.awt.Color(255, 255, 255));

        // Home - Labels
        home_con_status_lbl = new JLabel();
        icon_train_lbl = new JLabel("No of Trains ");
        icon_train_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        icon_train_lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/railway/images/trains.jpg")));
        icon_station_lbl = new JLabel("No of Stations ");
        icon_station_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        icon_station_lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/railway/images/stations.jpg")));
        icon_bookings_lbl = new JLabel("Total Bookings ");
        icon_bookings_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        icon_bookings_lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/railway/images/bookings.jpg")));
        dashboard_lbl = new JLabel("Dashboard");
        dashboard_lbl.setForeground(Color.WHITE);
        dashboard_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        count_trains_lbl = new JLabel();
        count_trains_lbl.setForeground(Color.BLACK);
        count_trains_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        count_stations_lbl = new JLabel();
        count_stations_lbl.setForeground(Color.BLACK);
        count_stations_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        count_bookings_lbl = new JLabel();
        count_bookings_lbl.setForeground(Color.BLACK);
        count_bookings_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        // Home - Buttons
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);

        home_refresh_jbtn = new JButton("Refresh");
        home_refresh_jbtn.setCursor(cur);
        home_refresh_jbtn.setBackground(Color.blue);
        home_refresh_jbtn.setForeground(Color.WHITE);
        home_refresh_jbtn.setFont(new Font("Arial", Font.PLAIN, 17));
        home_refresh_jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                refreshHomeButtonActionPerformed(evt);
            }
        });

        // Home Layout
        homeTab_jpa.setLayout(null);

        // Home Panels - attr
        count_trains_jpa.setBounds(100, 140, 300, 60);
        count_stations_jpa.setBounds(440, 140, 300, 60);
        count_bookings_jpa.setBounds(780, 140, 300, 60);

        // Home Default Label
        home_lbl = new JLabel("Home");

        // Home Default Label - attr
        home_lbl.setFont(new Font("Serif", Font.PLAIN, 35));
        home_lbl.setForeground(Color.WHITE);

        // Home components - attr
        home_lbl.setBounds(10, 20, 200, 40);

        // Home Labels - attr
        home_con_status_lbl.setBounds(750, 40, 300, 35);
        dashboard_lbl.setBounds(10, 80, 200, 30);

        // Home TextFields / ComboBox(es) / Buttons / Lists / ScrollPanes - attr
        home_refresh_jbtn.setBounds(1050, 40, 100, 30);

        // Adding Trains Panel items
        count_trains_jpa.add(icon_train_lbl);
        count_trains_jpa.add(count_trains_lbl);

        // Adding Stations Panel items
        count_stations_jpa.add(icon_station_lbl);
        count_stations_jpa.add(count_stations_lbl);

        // Adding Bookings Panel items
        count_bookings_jpa.add(icon_bookings_lbl);
        count_bookings_jpa.add(count_bookings_lbl);

        // Adding all Home tab variables
        homeTab_jpa.add(home_lbl);
        homeTab_jpa.add(home_con_status_lbl);
        homeTab_jpa.add(home_refresh_jbtn);
        homeTab_jpa.add(dashboard_lbl);
        homeTab_jpa.add(count_trains_jpa);
        homeTab_jpa.add(count_stations_jpa);
        homeTab_jpa.add(count_bookings_jpa);

        // Home Layout - attr
        homeTab_jpa.setLayout(null);

        // Adding Home Panel to Tabs
        Tabs.addTab("Home", homeTab_jpa);

        // ---------------------------------------------------------------------
    }

    // All Book Components & Attr
    private void allBookComponents() {

        // Book Panel initialization
        bookTab_jpa = new JPanel();
        bookTab_jpa.setBackground(new java.awt.Color(32, 38, 46));

        // Book - Labels
        passenger_details_lbl = new JLabel("Passenger Details");
        passenger_details_lbl.setForeground(Color.WHITE);
        passenger_details_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        fname_lbl = new JLabel("First Name :");
        fname_lbl.setForeground(Color.WHITE);
        fname_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        lname_lbl = new JLabel("Last Name :");
        lname_lbl.setForeground(Color.WHITE);
        lname_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        phoneNum_lbl = new JLabel("Phone Number :");
        phoneNum_lbl.setForeground(Color.WHITE);
        phoneNum_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        gender_lbl = new JLabel("Gender :");
        gender_lbl.setForeground(Color.WHITE);
        gender_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        booking_details_lbl = new JLabel("Booking Details");
        booking_details_lbl.setForeground(Color.WHITE);
        booking_details_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        title_lbl = new JLabel("Title :");
        title_lbl.setForeground(Color.WHITE);
        title_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        class_lbl = new JLabel("Class :");
        class_lbl.setForeground(Color.WHITE);
        class_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        no_of_seats_lbl = new JLabel("Seats :");
        no_of_seats_lbl.setForeground(Color.WHITE);
        no_of_seats_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        from_station_lbl = new JLabel("From Station :");
        from_station_lbl.setForeground(Color.WHITE);
        from_station_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        to_station_lbl = new JLabel("To Station :");
        to_station_lbl.setForeground(Color.WHITE);
        to_station_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        departure_lbl = new JLabel("Departure Time :");
        departure_lbl.setForeground(Color.WHITE);
        departure_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        departure_hh_lbl = new JLabel("HH");
        departure_hh_lbl.setForeground(Color.WHITE);
        departure_hh_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        departure_mm_lbl = new JLabel("MM");
        departure_mm_lbl.setForeground(Color.WHITE);
        departure_mm_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        arrival_lbl = new JLabel("Arrival Time :");
        arrival_lbl.setForeground(Color.WHITE);
        arrival_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        arrival_hh_lbl = new JLabel("HH");
        arrival_hh_lbl.setForeground(Color.WHITE);
        arrival_hh_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        arrival_mm_lbl = new JLabel("MM");
        arrival_mm_lbl.setForeground(Color.WHITE);
        arrival_mm_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        train_name_lbl = new JLabel("Train Name :");
        train_name_lbl.setForeground(Color.WHITE);
        train_name_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        train_number_lbl = new JLabel("Train Number :");
        train_number_lbl.setForeground(Color.WHITE);
        train_number_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        list_bookings_lbl = new JLabel("Added Bookings");
        list_bookings_lbl.setForeground(Color.WHITE);
        list_bookings_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        book_con_status_lbl = new JLabel();

        // Book - Textfields
        fname_txt = new JTextField(15);
        lname_txt = new JTextField(15);
        phoneNum_txt = new JTextField(15);
        no_of_seats_txt = new JTextField(15);
        departure_hh_txt = new JTextField(5);
        departure_mm_txt = new JTextField("00", 5);
        arrival_hh_txt = new JTextField(5);
        arrival_mm_txt = new JTextField("00", 5);
        // Listener to consume any string input if it's not a digit
        phoneNum_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        departure_hh_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        departure_mm_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        arrival_hh_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        arrival_mm_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        // Book - ComboBoxes
        gender_jcb = new JComboBox();
        gender_jcb.addItem("Male");
        gender_jcb.addItem("Female");

        title_jcb = new JComboBox();
        title_jcb.addItem("UMTT");
        title_jcb.addItem("DMTT");

        class_jcb = new JComboBox();
        class_jcb.addItem("First Class");
        class_jcb.addItem("Standard Class");

        from_station_jcb = new JComboBox();

        to_station_jcb = new JComboBox();

        train_name_jcb = new JComboBox();

        train_number_jcb = new JComboBox();

        departure_am_pm_jcb = new JComboBox();
        departure_am_pm_jcb.addItem("AM");
        departure_am_pm_jcb.addItem("PM");

        arrival_am_pm_jcb = new JComboBox();
        arrival_am_pm_jcb.addItem("AM");
        arrival_am_pm_jcb.addItem("PM");

        // Book - Buttons
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);

        submit_jbtn = new JButton("Add Ticket");
        submit_jbtn.setCursor(cur);
        submit_jbtn.setBackground(Color.cyan);
        submit_jbtn.setForeground(Color.BLACK);
        submit_jbtn.setFont(new Font("Arial", Font.PLAIN, 15));
        submit_jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        save_jbtn = new JButton("Save");
        save_jbtn.setCursor(cur);
        save_jbtn.setBackground(Color.GREEN);
        save_jbtn.setForeground(Color.BLACK);
        save_jbtn.setFont(new Font("Arial", Font.PLAIN, 17));
        save_jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        clear_jbtn = new JButton("Clear");
        clear_jbtn.setCursor(cur);
        clear_jbtn.setBackground(Color.RED);
        clear_jbtn.setForeground(Color.WHITE);
        clear_jbtn.setFont(new Font("Arial", Font.PLAIN, 17));
        clear_jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        // Book Lists / DefaultlistModels / Scrolls / Tables
        book_jl = new JList();

        dlm_book = new DefaultListModel();
        book_jl.setFont(new Font("Arial", Font.PLAIN, 15));
        book_jl.setModel(dlm_book);
        book_scroll = new JScrollPane(book_jl);

        // Book Layout
        bookTab_jpa.setLayout(null);

        // Book Default Label
        book_lbl = new JLabel("Book Ticket");

        // Book Default Label - attr
        book_lbl.setFont(new Font("Serif", Font.PLAIN, 35));
        book_lbl.setForeground(Color.WHITE);

        // Book components - attr
        book_lbl.setBounds(10, 20, 200, 40);

        // Book Labels - attr
        passenger_details_lbl.setBounds(10, 80, 200, 30);
        fname_lbl.setBounds(10, 120, 100, 30);
        lname_lbl.setBounds(420, 120, 100, 30);
        phoneNum_lbl.setBounds(10, 160, 150, 30);
        gender_lbl.setBounds(420, 160, 100, 30);
        booking_details_lbl.setBounds(10, 240, 200, 30);
        title_lbl.setBounds(10, 280, 100, 30);
        class_lbl.setBounds(420, 280, 100, 30);
        from_station_lbl.setBounds(10, 320, 100, 30);
        to_station_lbl.setBounds(420, 320, 100, 30);
        departure_lbl.setBounds(10, 360, 150, 30);
        departure_hh_lbl.setBounds(160, 360, 50, 30);
        departure_mm_lbl.setBounds(220, 360, 50, 30);
        arrival_lbl.setBounds(420, 360, 100, 30);
        arrival_hh_lbl.setBounds(550, 360, 50, 30);
        arrival_mm_lbl.setBounds(610, 360, 50, 30);
        train_name_lbl.setBounds(10, 400, 100, 30);
        train_number_lbl.setBounds(420, 400, 100, 30);
        no_of_seats_lbl.setBounds(10, 440, 100, 30);
        list_bookings_lbl.setBounds(800, 80, 200, 30);
        book_con_status_lbl.setBounds(750, 40, 300, 35);

        // Book TextFields / ComboBox(es) / Buttons / Lists / ScrollPanes - attr
        fname_txt.setBounds(160, 120, 200, 30);
        lname_txt.setBounds(550, 120, 200, 30);
        phoneNum_txt.setBounds(160, 160, 200, 30);
        gender_jcb.setBounds(550, 160, 200, 30);
        title_jcb.setBounds(160, 280, 200, 30);
        class_jcb.setBounds(550, 280, 200, 30);
        from_station_jcb.setBounds(160, 320, 200, 30);
        to_station_jcb.setBounds(550, 320, 200, 30);
        departure_hh_txt.setBounds(185, 360, 30, 30);
        departure_mm_txt.setBounds(245, 360, 30, 30);
        departure_am_pm_jcb.setBounds(280, 360, 50, 30);
        arrival_hh_txt.setBounds(575, 360, 30, 30);
        arrival_mm_txt.setBounds(635, 360, 30, 30);
        arrival_am_pm_jcb.setBounds(670, 360, 50, 30);
        train_name_jcb.setBounds(160, 400, 200, 30);
        train_number_jcb.setBounds(550, 400, 200, 30);
        no_of_seats_txt.setBounds(160, 440, 200, 30);
        submit_jbtn.setBounds(300, 550, 250, 35);
        book_scroll.setBounds(800, 120, 350, 460); // after adding the list you put the list in the scrollpane then set bounds for the scrollpane
        save_jbtn.setBounds(860, 600, 100, 35);
        clear_jbtn.setBounds(1000, 600, 100, 35);

        // Adding all Book tab variables
        bookTab_jpa.add(book_lbl);
        bookTab_jpa.add(booking_details_lbl);
        bookTab_jpa.add(fname_lbl);
        bookTab_jpa.add(fname_txt);
        bookTab_jpa.add(lname_lbl);
        bookTab_jpa.add(lname_txt);
        bookTab_jpa.add(phoneNum_lbl);
        bookTab_jpa.add(phoneNum_txt);
        bookTab_jpa.add(gender_lbl);
        bookTab_jpa.add(gender_jcb);
        bookTab_jpa.add(passenger_details_lbl);
        bookTab_jpa.add(title_lbl);
        bookTab_jpa.add(title_jcb);
        bookTab_jpa.add(class_lbl);
        bookTab_jpa.add(class_jcb);
        bookTab_jpa.add(from_station_lbl);
        bookTab_jpa.add(from_station_jcb);
        bookTab_jpa.add(to_station_lbl);
        bookTab_jpa.add(to_station_jcb);
        bookTab_jpa.add(departure_lbl);
        bookTab_jpa.add(departure_hh_lbl);
        bookTab_jpa.add(departure_hh_txt);
        bookTab_jpa.add(departure_mm_lbl);
        bookTab_jpa.add(departure_mm_txt);
        bookTab_jpa.add(departure_am_pm_jcb);
        bookTab_jpa.add(arrival_lbl);
        bookTab_jpa.add(arrival_hh_lbl);
        bookTab_jpa.add(arrival_hh_txt);
        bookTab_jpa.add(arrival_mm_lbl);
        bookTab_jpa.add(arrival_mm_txt);
        bookTab_jpa.add(arrival_am_pm_jcb);
        bookTab_jpa.add(train_name_lbl);
        bookTab_jpa.add(train_name_jcb);
        bookTab_jpa.add(train_number_lbl);
        bookTab_jpa.add(train_number_jcb);
        bookTab_jpa.add(no_of_seats_lbl);
        bookTab_jpa.add(no_of_seats_txt);
        bookTab_jpa.add(submit_jbtn);
        bookTab_jpa.add(book_con_status_lbl);
        bookTab_jpa.add(list_bookings_lbl);
        bookTab_jpa.add(book_scroll); // adding the scrollpane
        bookTab_jpa.add(save_jbtn);
        bookTab_jpa.add(clear_jbtn);

        // Book Layout - attr
        bookTab_jpa.setLayout(null);

        // Adding Book Panel to Tabs
        Tabs.addTab("Book Ticket", bookTab_jpa);

        // ---------------------------------------------------------------------
    }

    // All Bookings Components & Attr
    private void allBookingsComponents() {

        // Bookings Panel initialization
        bookingsTab_jpa = new JPanel();
        bookingsTab_jpa.setBackground(new java.awt.Color(32, 38, 46));

        // Bookings - Labels
        bookings_con_status_lbl = new JLabel();

        // Bookings - TextFields
        search_txt = new JTextField(15);
        search_txt.setToolTipText("Search bookings for - T/No, Firstname, Stations, Train Numbers ...");
        // Used to search automatically once you press the enter button on the search bar
        search_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character c = e.getKeyChar();
                if (c == KeyEvent.VK_ENTER) {
                    search();
                }
            }
        });

        // Bookings - ComboBoxes
        filter_jcb = new JComboBox();
        filter_jcb.setToolTipText("Filter data by");
        filter_jcb.addItem("Today");
        filter_jcb.addItem("A week ago");
        filter_jcb.addItem("A month ago");
        filter_jcb.addItem("A year ago");
        filter_jcb.addItem("A long time ago");
        filter_jcb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                synchronized (this) {
                    filterItemChanged();
                }
            }
        });

        // Bookings - Buttons
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);

        bookings_reload_jbtn = new JButton("Refresh");
        bookings_reload_jbtn.setCursor(cur);
        bookings_reload_jbtn.setBackground(Color.blue);
        bookings_reload_jbtn.setForeground(Color.WHITE);
        bookings_reload_jbtn.setFont(new Font("Arial", Font.PLAIN, 17));
        bookings_reload_jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                reload_bookingsButtonActionPerformed(evt);
            }
        });

        search_jbtn = new JButton();
        search_jbtn.setToolTipText("Search ...");
        search_jbtn.setCursor(cur);
        search_jbtn.setBackground(new java.awt.Color(32, 38, 46));
        search_jbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/railway/images/search.jpg")));
        search_jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        // Bookings Lists / DefaultlistModels / Scrolls / Tables
        bookings_tbl = new JTable();
        bookings_tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        dtm_bookings = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        bookings_tbl.setModel(dtm_bookings);
        bookings_tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseDoubleClicked(e);
                mouseRightClick(e);
            }

            public void mouseReleased(MouseEvent e) {
                mouseRightClick(e);
            }

        });
        bookings_scroll = new JScrollPane();
        // Creates a viewport if necessary and then sets its view
        bookings_scroll.setViewportView(bookings_tbl);

        // Bookings Layout
        bookingsTab_jpa.setLayout(null);

        // Bookings Default Label
        bookings_lbl = new JLabel("All Bookings");

        // Bookings Default Label - attr
        bookings_lbl.setFont(new Font("Serif", Font.PLAIN, 35));
        bookings_lbl.setForeground(Color.WHITE);

        // Bookings components - attr
        bookings_lbl.setBounds(10, 20, 200, 40);

        // Bookings Labels - attr
        bookings_con_status_lbl.setBounds(750, 40, 300, 35);

        // Bookings Table - attr
        dtm_bookings.addColumn("T/No");
        dtm_bookings.addColumn("Name");
        dtm_bookings.addColumn("Phone Number");
        dtm_bookings.addColumn("Gender");
        dtm_bookings.addColumn("Title");
        dtm_bookings.addColumn("Class");
        dtm_bookings.addColumn("Seats");
        dtm_bookings.addColumn("From Station");
        dtm_bookings.addColumn("Departure Time");
        dtm_bookings.addColumn("To Station");
        dtm_bookings.addColumn("Arrival Time");
        dtm_bookings.addColumn("Train Number");
        dtm_bookings.addColumn("Train Name");
        dtm_bookings.addColumn("Date");

        bookings_tbl.getTableHeader().setReorderingAllowed(false);
        bookings_tbl.getTableHeader().setResizingAllowed(false);

        // Bookings TextFields / ComboBox(es) / Buttons / Lists / ScrollPanes - attr
        search_txt.setBounds(320, 38, 230, 35);
        search_jbtn.setBounds(545, 40, 40, 31);
        filter_jcb.setBounds(620, 40, 110, 30);
        bookings_reload_jbtn.setBounds(1050, 40, 100, 30);
        bookings_scroll.setBounds(10, 80, 1170, 600); // after adding the member panel you put the panel in the scrollpane then set bounds for the scrollpane

        // Adding all Bookings tab variables
        bookingsTab_jpa.add(bookings_lbl);
        bookingsTab_jpa.add(bookings_con_status_lbl);
        bookingsTab_jpa.add(search_txt);
        bookingsTab_jpa.add(search_jbtn);
        bookingsTab_jpa.add(filter_jcb);
        bookingsTab_jpa.add(bookings_reload_jbtn);
        bookingsTab_jpa.add(bookings_scroll);

        // Bookings Layout - attr
        bookingsTab_jpa.setLayout(null);

        // Adding Booking Panel to Tabs
        Tabs.addTab("Bookings", bookingsTab_jpa);

        // ---------------------------------------------------------------------
    }

    // All Add Stations Components & Attr
    private void allAddStationsComponents() {

        // Add Station Panel initialization
        addStationTab_jpa = new JPanel();
        addStationTab_jpa.setBackground(new java.awt.Color(32, 38, 46));

        // Add Station - Labels
        add_station_details_lbl = new JLabel("Station Details");
        add_station_details_lbl.setForeground(Color.WHITE);
        add_station_details_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        add_station_name_lbl = new JLabel("Station Name : ");
        add_station_name_lbl.setForeground(Color.WHITE);
        add_station_name_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        add_station_type_lbl = new JLabel("Station Type : ");
        add_station_type_lbl.setForeground(Color.WHITE);
        add_station_type_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        add_station_con_status_lbl = new JLabel();

        // Add Station - TextFields
        add_station_name_txt = new JTextField(50);
        add_station_type_txt = new JTextField(50);
        add_station_type_txt.setToolTipText("Halt / Terminal");

        // Add Station - Buttons
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);

        create_station_jbtn = new JButton("Create Station");
        create_station_jbtn.setCursor(cur);
        create_station_jbtn.setBackground(Color.GREEN);
        create_station_jbtn.setForeground(Color.BLACK);
        create_station_jbtn.setFont(new Font("Arial", Font.PLAIN, 17));
        create_station_jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                createStationButtonActionPerformed(evt);
            }
        });

        // Add Station Layout
        addStationTab_jpa.setLayout(null);

        // Add Station Default Label
        add_station_lbl = new JLabel("Add Station");

        // Add Station Default Label - attr
        add_station_lbl.setFont(new Font("Serif", Font.PLAIN, 35));
        add_station_lbl.setForeground(Color.WHITE);

        // Add Station components - attr
        add_station_lbl.setBounds(10, 20, 200, 40);

        // Add Station Labels - attr
        add_station_details_lbl.setBounds(10, 80, 200, 30);
        add_station_name_lbl.setBounds(10, 120, 150, 30);
        add_station_type_lbl.setBounds(10, 160, 150, 30);
        add_station_con_status_lbl.setBounds(750, 40, 300, 35);

        // Add Station TextFields / ComboBox(es) / Buttons / Lists / ScrollPanes - attr
        add_station_name_txt.setBounds(170, 120, 400, 30);
        add_station_type_txt.setBounds(170, 160, 400, 30);
        create_station_jbtn.setBounds(200, 220, 200, 30);

        // Adding all Add Station tab variables
        addStationTab_jpa.add(add_station_lbl);
        addStationTab_jpa.add(add_station_details_lbl);
        addStationTab_jpa.add(add_station_name_lbl);
        addStationTab_jpa.add(add_station_name_txt);
        addStationTab_jpa.add(add_station_type_lbl);
        addStationTab_jpa.add(add_station_type_txt);
        addStationTab_jpa.add(create_station_jbtn);
        addStationTab_jpa.add(add_station_con_status_lbl);

        // Add Station Layout - attr
        addStationTab_jpa.setLayout(null);

        // Adding Add Station Panel to Tabs
        Tabs.addTab("Add Station", addStationTab_jpa);

        // ---------------------------------------------------------------------
    }

    // All Stations Components & Attr
    private void allStationsComponents() {

        // Stations Panel initialization
        stations_jpa = new JPanel();
        stations_jpa.setBackground(new java.awt.Color(32, 38, 46));

        // Stations - Labels
        stations_con_status_lbl = new JLabel();

        // Stations - Buttons
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);

        stations_reload_jbtn = new JButton("Refresh");
        stations_reload_jbtn.setCursor(cur);
        stations_reload_jbtn.setBackground(Color.BLUE);
        stations_reload_jbtn.setForeground(Color.WHITE);
        stations_reload_jbtn.setFont(new Font("Arial", Font.PLAIN, 17));
        stations_reload_jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                reloadStationsButtonActionPerformed(evt);
            }
        });

        // Stations Lists / DefaultlistModels / Scrolls / Tables
        stations_tbl = new JTable();
        // Disable all cells from been editable
        dtm_stations = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        stations_tbl.setModel(dtm_stations);
        stations_scroll = new JScrollPane();
        // Creates a viewport if necessary and then sets its view
        stations_scroll.setViewportView(stations_tbl);

        // Stations Layout
        stations_jpa.setLayout(null);

        // Stations Default Label
        stations_lbl = new JLabel("Stations");

        // Stations Default Label - attr
        stations_lbl.setFont(new Font("Serif", Font.PLAIN, 35));
        stations_lbl.setForeground(Color.WHITE);

        // Stations components - attr
        stations_lbl.setBounds(10, 20, 200, 40);

        // Stations Labels - attr 
        stations_con_status_lbl.setBounds(750, 40, 300, 35);

        // Stations Table - attr
        dtm_stations.addColumn("S/N");
        dtm_stations.addColumn("Station Name");
        dtm_stations.addColumn("Station Type");
        dtm_stations.addColumn("Date");

        stations_tbl.getTableHeader().setReorderingAllowed(false);

        // Stations TextFields / ComboBox(es) / Buttons / Lists / ScrollPanes - attr
        stations_reload_jbtn.setBounds(1050, 40, 100, 30);
        stations_scroll.setBounds(10, 80, 1170, 600); // after adding the member panel you put the panel in the scrollpane then set bounds for the scrollpane

        // Adding all Stations tab variables
        stations_jpa.add(stations_lbl);
        stations_jpa.add(stations_reload_jbtn);
        stations_jpa.add(stations_con_status_lbl);
        stations_jpa.add(stations_scroll);

        // Stations Layout - attr
        stations_jpa.setLayout(null);

        // Adding Stations Panel to Tabs
        Tabs.addTab("Stations", stations_jpa);

        // ---------------------------------------------------------------------
    }

    // All Add Trains Components & Attr
    private void allAddTrainsComponents() {

        // Add Train Panel initialization
        addTrainTab_jpa = new JPanel();
        addTrainTab_jpa.setBackground(new java.awt.Color(32, 38, 46));

        // Add Train - Labels
        add_train_details_lbl = new JLabel("Train Details");
        add_train_details_lbl.setForeground(Color.WHITE);
        add_train_details_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        add_train_name_lbl = new JLabel("Train Name : ");
        add_train_name_lbl.setForeground(Color.WHITE);
        add_train_name_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        add_train_number_lbl = new JLabel("Train Number : ");
        add_train_number_lbl.setForeground(Color.WHITE);
        add_train_number_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        add_train_con_status_lbl = new JLabel();

        // Add Train - TextFields
        add_train_name_txt = new JTextField(50);
        add_train_number_txt = new JTextField(50);
        // Listener to consume any string input if it's not a digit
        add_train_number_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        // Add Train - Buttons
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);

        create_train_jbtn = new JButton("Create Train");
        create_train_jbtn.setCursor(cur);
        create_train_jbtn.setBackground(Color.GREEN);
        create_train_jbtn.setForeground(Color.BLACK);
        create_train_jbtn.setFont(new Font("Arial", Font.PLAIN, 17));
        create_train_jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                createTrainButtonActionPerformed(evt);
            }
        });

        // Add Train Layout
        addTrainTab_jpa.setLayout(null);

        // Add Train Default Label
        add_train_lbl = new JLabel("Add Train");

        // Add Train Default Label - attr
        add_train_lbl.setFont(new Font("Serif", Font.PLAIN, 35));
        add_train_lbl.setForeground(Color.WHITE);

        // Add Train components - attr
        add_train_lbl.setBounds(10, 20, 200, 40);

        // Add Train Labels - attr
        add_train_details_lbl.setBounds(10, 80, 200, 30);
        add_train_name_lbl.setBounds(10, 120, 150, 30);
        add_train_number_lbl.setBounds(10, 160, 150, 30);
        add_train_con_status_lbl.setBounds(750, 40, 300, 35);

        // Add Train TextFields / ComboBox(es) / Buttons / Lists / ScrollPanes - attr
        add_train_name_txt.setBounds(170, 120, 400, 30);
        add_train_number_txt.setBounds(170, 160, 400, 30);
        create_train_jbtn.setBounds(200, 220, 200, 30);

        // Adding all Add Train tab variables
        addTrainTab_jpa.add(add_train_lbl);
        addTrainTab_jpa.add(add_train_details_lbl);
        addTrainTab_jpa.add(add_train_name_lbl);
        addTrainTab_jpa.add(add_train_name_txt);
        addTrainTab_jpa.add(add_train_number_lbl);
        addTrainTab_jpa.add(add_train_number_txt);
        addTrainTab_jpa.add(create_train_jbtn);
        addTrainTab_jpa.add(add_train_con_status_lbl);

        // Add Train Layout - attr
        addTrainTab_jpa.setLayout(null);

        // Adding Add Train Panel to Tabs
        Tabs.addTab("Add Train", addTrainTab_jpa);

        // ---------------------------------------------------------------------
    }

    // All Trains Components & Attr
    private void allTrainsComponents() {

        // Trains Panel initialization
        trains_jpa = new JPanel();
        trains_jpa.setBackground(new java.awt.Color(32, 38, 46));

        // Trains - Labels
        trains_con_status_lbl = new JLabel();

        // Trains - Buttons
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);

        trains_reload_jbtn = new JButton("Refresh");
        trains_reload_jbtn.setCursor(cur);
        trains_reload_jbtn.setBackground(Color.BLUE);
        trains_reload_jbtn.setForeground(Color.WHITE);
        trains_reload_jbtn.setFont(new Font("Arial", Font.PLAIN, 17));
        trains_reload_jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                reloadTrainsButtonActionPerformed(evt);
            }
        });

        // Trains Lists / DefaultlistModels / Scrolls / Tables
        trains_tbl = new JTable();
        dtm_trains = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        trains_tbl.setModel(dtm_trains);
        trains_scroll = new JScrollPane();
        // Creates a viewport if necessary and then sets its view
        trains_scroll.setViewportView(trains_tbl);

        // Trains Layout
        trains_jpa.setLayout(null);

        // Trains Default Label
        trains_lbl = new JLabel("Trains");

        // Trains Default Label - attr
        trains_lbl.setFont(new Font("Serif", Font.PLAIN, 35));
        trains_lbl.setForeground(Color.WHITE);

        // Trains components - attr
        trains_lbl.setBounds(10, 20, 200, 40);

        // Trains Labels - attr
        trains_con_status_lbl.setBounds(750, 40, 300, 35);

        // Stations Table - attr
        dtm_trains.addColumn("S/N");
        dtm_trains.addColumn("Train Name");
        dtm_trains.addColumn("Train Number");
        dtm_trains.addColumn("Date");

        trains_tbl.getTableHeader().setReorderingAllowed(false);

        // Trains TextFields / ComboBox(es) / Buttons / Lists / ScrollPanes - attr
        trains_reload_jbtn.setBounds(1050, 40, 100, 30);
        trains_scroll.setBounds(10, 80, 1170, 600); // after adding the member panel you put the panel in the scrollpane then set bounds for the scrollpane

        // Adding all Trains tab variables
        trains_jpa.add(trains_lbl);
        trains_jpa.add(trains_reload_jbtn);
        trains_jpa.add(trains_con_status_lbl);
        trains_jpa.add(trains_scroll);

        // Trains Layout - attr
        trains_jpa.setLayout(null);

        // Adding Trains Panel to Tabs
        Tabs.addTab("Trains", trains_jpa);

        // ---------------------------------------------------------------------
    }

    // All About Us Components & Attr
    private void allAboutUsComponents() {

        // About Us Panel initializatiion
        aboutTab_jpa = new JPanel();
        aboutTab_jpa.setBackground(new java.awt.Color(32, 38, 46));

        // About Us - Labels 
        about_con_status_lbl = new JLabel();
        nigeria_flag_lbl = new JLabel();
        nigeria_flag_lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/railway/images/Eagle.jpg")));
        rivers_lbl = new JLabel("                      Railway Service");
        rivers_lbl.setForeground(Color.blue);
        rivers_lbl.setFont(new Font("Arial", Font.PLAIN, 23));
        poweredby_lbl = new JLabel("Powered By");
        poweredby_lbl.setForeground(Color.cyan);
        poweredby_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        emmynem_lbl = new JLabel("Emmynem");
        emmynem_lbl.setToolTipText("Visit Us @ https://github.com/Emmynem/");
        emmynem_lbl.setForeground(Color.WHITE);
        emmynem_lbl.setFont(new Font("Arial", Font.PLAIN, 25));

        // About Us Layout
        aboutTab_jpa.setLayout(null);

        // About Us Default Label
        about_lbl = new JLabel("About Us");

        // About Us Default Label - attr
        about_lbl.setFont(new Font("Serif", Font.PLAIN, 35));
        about_lbl.setForeground(Color.WHITE);

        // About Us components - attr
        about_lbl.setBounds(10, 20, 200, 40);

        // About Us Labels - attr
        about_con_status_lbl.setBounds(750, 40, 300, 35);
        nigeria_flag_lbl.setBounds(500, 160, 200, 200);
        rivers_lbl.setBounds(390, 360, 500, 40);
        poweredby_lbl.setBounds(530, 550, 200, 40);
        emmynem_lbl.setBounds(520, 600, 200, 40);

        // Adding all About Us tab variables
        aboutTab_jpa.add(about_lbl);
        aboutTab_jpa.add(about_con_status_lbl);
        aboutTab_jpa.add(nigeria_flag_lbl);
        aboutTab_jpa.add(rivers_lbl);
        aboutTab_jpa.add(poweredby_lbl);
        aboutTab_jpa.add(emmynem_lbl);

        // About Us Layout - attr
        aboutTab_jpa.setLayout(null);

        // Adding About Us Panel to Tabs
        Tabs.addTab("About Us", aboutTab_jpa);

        // ---------------------------------------------------------------------
    }

    // All View Bookings Components & attr
    private void allViewBookingComponents() {
        // Creating the View Frame
        ViewFrame = new JFrame();

        // View Panel initialization
        view_jpa = new JPanel();
        view_jpa.setBackground(new java.awt.Color(32, 38, 46));

        // View - Labels 
        view_name_lbl = new JLabel();
        view_name_lbl.setToolTipText("Passenger Name");

        view_date_lbl = new JLabel();
        view_date_lbl.setToolTipText("Book Date");

        view_t_no_lbl = new JLabel();
        view_t_no_lbl.setToolTipText("Ticket Number");

        view_phoneNum_lbl = new JLabel("Mobile : ");
        view_phoneNum_lbl.setToolTipText("Phone Number");
        view_phoneNum_value_lbl = new JLabel();

        view_gender_lbl = new JLabel("Gender : ");
        view_gender_lbl.setToolTipText("Gender");
        view_gender_value_lbl = new JLabel();

        view_seperator_line_lbl
                = new JLabel("----------------------------------------------------"
                        + "--------------------------------------------------------");

        view_title_lbl = new JLabel("Title : ");
        view_title_lbl.setToolTipText("Title");
        view_title_value_lbl = new JLabel();

        view_class_lbl = new JLabel("Class : ");
        view_class_lbl.setToolTipText("Class");
        view_class_value_lbl = new JLabel();

        view_no_of_seats_lbl = new JLabel("Seats : ");
        view_no_of_seats_lbl.setToolTipText("Number of Seats");
        view_no_of_seats_value_lbl = new JLabel();

        view_from_station_lbl = new JLabel("From : ");
        view_from_station_lbl.setToolTipText("From Station");
        view_from_station_value_lbl = new JLabel();

        view_to_station_lbl = new JLabel("At : ");
        view_to_station_lbl.setToolTipText("To Station");
        view_to_station_value_lbl = new JLabel();

        view_departure_lbl = new JLabel("Depart : ");
        view_departure_lbl.setToolTipText("Departure Time");
        view_departure_value_lbl = new JLabel();

        view_arrival_lbl = new JLabel("Arrive : ");
        view_arrival_lbl.setToolTipText("Arrvial Time");
        view_arrival_value_lbl = new JLabel();

        view_train_name_lbl = new JLabel("T / Name : ");
        view_train_name_lbl.setToolTipText("Train Name");
        view_train_name_value_lbl = new JLabel();

        view_train_number_lbl = new JLabel("T / No : ");
        view_train_number_lbl.setToolTipText("Train Number");
        view_train_number_value_lbl = new JLabel();

        view_seperator_line_2_lbl
                = new JLabel("----------------------------------------------------"
                        + "--------------------------------------------------------");

        // View Layout
        view_jpa.setLayout(null);

        // View Labels - attr
        view_name_lbl.setFont(new Font("Serif", Font.PLAIN, 30));
        view_name_lbl.setForeground(Color.WHITE);

        view_date_lbl.setForeground(Color.WHITE);
        view_date_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_t_no_lbl.setForeground(Color.WHITE);
        view_t_no_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_phoneNum_lbl.setForeground(Color.WHITE);
        view_phoneNum_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        view_phoneNum_value_lbl.setForeground(Color.WHITE);
        view_phoneNum_value_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_gender_lbl.setForeground(Color.WHITE);
        view_gender_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        view_gender_value_lbl.setForeground(Color.WHITE);
        view_gender_value_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_seperator_line_lbl.setForeground(Color.WHITE);
        view_seperator_line_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_title_lbl.setForeground(Color.WHITE);
        view_title_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        view_title_value_lbl.setForeground(Color.WHITE);
        view_title_value_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_class_lbl.setForeground(Color.WHITE);
        view_class_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        view_class_value_lbl.setForeground(Color.WHITE);
        view_class_value_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_no_of_seats_lbl.setForeground(Color.WHITE);
        view_no_of_seats_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        view_no_of_seats_value_lbl.setForeground(Color.WHITE);
        view_no_of_seats_value_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_from_station_lbl.setForeground(Color.WHITE);
        view_from_station_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        view_from_station_value_lbl.setForeground(Color.WHITE);
        view_from_station_value_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_to_station_lbl.setForeground(Color.WHITE);
        view_to_station_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        view_to_station_value_lbl.setForeground(Color.WHITE);
        view_to_station_value_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_departure_lbl.setForeground(Color.WHITE);
        view_departure_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        view_departure_value_lbl.setForeground(Color.WHITE);
        view_departure_value_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_arrival_lbl.setForeground(Color.WHITE);
        view_arrival_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        view_arrival_value_lbl.setForeground(Color.WHITE);
        view_arrival_value_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_train_name_lbl.setForeground(Color.WHITE);
        view_train_name_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        view_train_name_value_lbl.setForeground(Color.WHITE);
        view_train_name_value_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_train_number_lbl.setForeground(Color.WHITE);
        view_train_number_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        view_train_number_value_lbl.setForeground(Color.WHITE);
        view_train_number_value_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        view_seperator_line_2_lbl.setForeground(Color.WHITE);
        view_seperator_line_2_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        // View components - attr
        view_name_lbl.setBounds(10, 20, 400, 40);
        view_date_lbl.setBounds(720, 60, 100, 30);
        view_t_no_lbl.setBounds(740, 90, 100, 30);
        view_phoneNum_lbl.setBounds(10, 140, 100, 30);
        view_phoneNum_value_lbl.setBounds(130, 140, 150, 30);
        view_gender_lbl.setBounds(300, 140, 100, 30);
        view_gender_value_lbl.setBounds(400, 140, 150, 30);
        view_seperator_line_lbl.setBounds(50, 200, 780, 30);
        view_title_lbl.setBounds(10, 250, 100, 30);
        view_title_value_lbl.setBounds(130, 250, 150, 30);
        view_class_lbl.setBounds(300, 250, 100, 30);
        view_class_value_lbl.setBounds(400, 250, 200, 30);
        view_departure_lbl.setBounds(10, 300, 100, 30);
        view_departure_value_lbl.setBounds(130, 300, 150, 30);
        view_from_station_lbl.setBounds(300, 300, 100, 30);
        view_from_station_value_lbl.setBounds(400, 300, 400, 30);
        view_arrival_lbl.setBounds(10, 350, 100, 30);
        view_arrival_value_lbl.setBounds(130, 350, 150, 30);
        view_to_station_lbl.setBounds(300, 350, 100, 30);
        view_to_station_value_lbl.setBounds(400, 350, 400, 30);
        view_train_number_lbl.setBounds(10, 400, 100, 30);
        view_train_number_value_lbl.setBounds(130, 400, 150, 30);
        view_train_name_lbl.setBounds(300, 400, 100, 30);
        view_train_name_value_lbl.setBounds(400, 400, 400, 30);
        view_no_of_seats_lbl.setBounds(10, 450, 100, 30);
        view_no_of_seats_value_lbl.setBounds(130, 450, 150, 30);
        view_seperator_line_2_lbl.setBounds(50, 520, 780, 30);

        // View Layout - attr
        view_jpa.setLayout(null);

        // Adding all coponents to View Panel
        view_jpa.add(view_name_lbl);
        view_jpa.add(view_date_lbl);
        view_jpa.add(view_t_no_lbl);
        view_jpa.add(view_phoneNum_lbl);
        view_jpa.add(view_phoneNum_value_lbl);
        view_jpa.add(view_gender_lbl);
        view_jpa.add(view_gender_value_lbl);
        view_jpa.add(view_seperator_line_lbl);
        view_jpa.add(view_gender_value_lbl);
        view_jpa.add(view_title_lbl);
        view_jpa.add(view_title_value_lbl);
        view_jpa.add(view_class_lbl);
        view_jpa.add(view_class_value_lbl);
        view_jpa.add(view_departure_lbl);
        view_jpa.add(view_departure_value_lbl);
        view_jpa.add(view_from_station_lbl);
        view_jpa.add(view_from_station_value_lbl);
        view_jpa.add(view_arrival_lbl);
        view_jpa.add(view_arrival_value_lbl);
        view_jpa.add(view_to_station_lbl);
        view_jpa.add(view_to_station_value_lbl);
        view_jpa.add(view_train_number_lbl);
        view_jpa.add(view_train_number_value_lbl);
        view_jpa.add(view_train_name_lbl);
        view_jpa.add(view_train_name_value_lbl);
        view_jpa.add(view_no_of_seats_lbl);
        view_jpa.add(view_no_of_seats_value_lbl);
        view_jpa.add(view_seperator_line_2_lbl);

        // Adding View Panel to View Frame
        ViewFrame.add(view_jpa);

        ViewFrame.setSize(850, 650);
        ViewFrame.setTitle("View Ticket");
        ViewFrame.setVisible(true);
        ViewFrame.setLocationRelativeTo(null);
        ViewFrame.setResizable(false);
        ViewFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/railway/images/Icon 3.PNG")));
        ViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // All Edit Bookings Components & attr
    private void allEditBookingComponents() {

        // Creating the Edit Frame
        EditFrame = new JFrame();

        // Edit Panel initialization
        edit_jpa = new JPanel();
        edit_jpa.setBackground(new java.awt.Color(32, 38, 46));

        // Book - Labels
        passenger_details_lbl = new JLabel("Passenger Details");
        passenger_details_lbl.setForeground(Color.WHITE);
        passenger_details_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        fname_lbl = new JLabel("First Name :");
        fname_lbl.setForeground(Color.WHITE);
        fname_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        lname_lbl = new JLabel("Last Name :");
        lname_lbl.setForeground(Color.WHITE);
        lname_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        phoneNum_lbl = new JLabel("Phone Number :");
        phoneNum_lbl.setForeground(Color.WHITE);
        phoneNum_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        gender_lbl = new JLabel("Gender :");
        gender_lbl.setForeground(Color.WHITE);
        gender_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        booking_details_lbl = new JLabel("Booking Details");
        booking_details_lbl.setForeground(Color.WHITE);
        booking_details_lbl.setFont(new Font("Arial", Font.PLAIN, 20));

        title_lbl = new JLabel("Title :");
        title_lbl.setForeground(Color.WHITE);
        title_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        class_lbl = new JLabel("Class :");
        class_lbl.setForeground(Color.WHITE);
        class_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        no_of_seats_lbl = new JLabel("Seats :");
        no_of_seats_lbl.setForeground(Color.WHITE);
        no_of_seats_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        from_station_lbl = new JLabel("From Station :");
        from_station_lbl.setForeground(Color.WHITE);
        from_station_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        to_station_lbl = new JLabel("To Station :");
        to_station_lbl.setForeground(Color.WHITE);
        to_station_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        departure_lbl = new JLabel("Departure Time :");
        departure_lbl.setForeground(Color.WHITE);
        departure_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        departure_hh_lbl = new JLabel("HH");
        departure_hh_lbl.setForeground(Color.WHITE);
        departure_hh_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        departure_mm_lbl = new JLabel("MM");
        departure_mm_lbl.setForeground(Color.WHITE);
        departure_mm_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        arrival_lbl = new JLabel("Arrival Time :");
        arrival_lbl.setForeground(Color.WHITE);
        arrival_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        arrival_hh_lbl = new JLabel("HH");
        arrival_hh_lbl.setForeground(Color.WHITE);
        arrival_hh_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        arrival_mm_lbl = new JLabel("MM");
        arrival_mm_lbl.setForeground(Color.WHITE);
        arrival_mm_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        train_name_lbl = new JLabel("Train Name :");
        train_name_lbl.setForeground(Color.WHITE);
        train_name_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        train_number_lbl = new JLabel("Train Number :");
        train_number_lbl.setForeground(Color.WHITE);
        train_number_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        list_bookings_lbl = new JLabel("Added Bookings");
        list_bookings_lbl.setForeground(Color.WHITE);
        list_bookings_lbl.setFont(new Font("Arial", Font.PLAIN, 15));

//        book_con_status_lbl = new JLabel();
        // Book - Textfields
        edit_fname_txt = new JTextField(15);
        edit_lname_txt = new JTextField(15);
        edit_phoneNum_txt = new JTextField(15);
        edit_no_of_seats_txt = new JTextField(15);
        edit_departure_hh_txt = new JTextField(5);
        edit_departure_mm_txt = new JTextField("00", 5);
        edit_arrival_hh_txt = new JTextField(5);
        edit_arrival_mm_txt = new JTextField("00", 5);
        // Listener to consume any string input if it's not a digit
        edit_phoneNum_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        edit_departure_hh_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        edit_departure_mm_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        edit_arrival_hh_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        edit_arrival_mm_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        // Book - ComboBoxes
        edit_gender_jcb = new JComboBox();
        edit_gender_jcb.addItem("Male");
        edit_gender_jcb.addItem("Female");

        edit_title_jcb = new JComboBox();
        edit_title_jcb.addItem("UMTT");
        edit_title_jcb.addItem("DMTT");

        edit_class_jcb = new JComboBox();
        edit_class_jcb.addItem("First Class");
        edit_class_jcb.addItem("Standard Class");

        edit_from_station_jcb = new JComboBox();

        edit_to_station_jcb = new JComboBox();

        edit_train_name_jcb = new JComboBox();

        edit_train_number_jcb = new JComboBox();

        edit_departure_am_pm_jcb = new JComboBox();
        edit_departure_am_pm_jcb.addItem("AM");
        edit_departure_am_pm_jcb.addItem("PM");

        edit_arrival_am_pm_jcb = new JComboBox();
        edit_arrival_am_pm_jcb.addItem("AM");
        edit_arrival_am_pm_jcb.addItem("PM");

        // Book - Buttons
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);

        edit_submit_jbtn = new JButton("Save Changes");
        edit_submit_jbtn.setCursor(cur);
        edit_submit_jbtn.setBackground(Color.cyan);
        edit_submit_jbtn.setForeground(Color.BLACK);
        edit_submit_jbtn.setFont(new Font("Arial", Font.PLAIN, 15));
        edit_submit_jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveChangesButtonActionPerformed(evt);
            }
        });

        // Book Labels - attr
        passenger_details_lbl.setBounds(10, 80, 200, 30);
        fname_lbl.setBounds(10, 120, 100, 30);
        lname_lbl.setBounds(420, 120, 100, 30);
        phoneNum_lbl.setBounds(10, 160, 150, 30);
        gender_lbl.setBounds(420, 160, 100, 30);
        booking_details_lbl.setBounds(10, 240, 200, 30);
        title_lbl.setBounds(10, 280, 100, 30);
        class_lbl.setBounds(420, 280, 100, 30);
        from_station_lbl.setBounds(10, 320, 100, 30);
        to_station_lbl.setBounds(420, 320, 100, 30);
        departure_lbl.setBounds(10, 360, 150, 30);
        departure_hh_lbl.setBounds(160, 360, 50, 30);
        departure_mm_lbl.setBounds(220, 360, 50, 30);
        arrival_lbl.setBounds(420, 360, 100, 30);
        arrival_hh_lbl.setBounds(550, 360, 50, 30);
        arrival_mm_lbl.setBounds(610, 360, 50, 30);
        train_name_lbl.setBounds(10, 400, 100, 30);
        train_number_lbl.setBounds(420, 400, 100, 30);
        no_of_seats_lbl.setBounds(10, 440, 100, 30);

        // Book TextFields / ComboBox(es) / Buttons / Lists / ScrollPanes - attr
        edit_fname_txt.setBounds(160, 120, 200, 30);
        edit_lname_txt.setBounds(550, 120, 200, 30);
        edit_phoneNum_txt.setBounds(160, 160, 200, 30);
        edit_gender_jcb.setBounds(550, 160, 200, 30);
        edit_title_jcb.setBounds(160, 280, 200, 30);
        edit_class_jcb.setBounds(550, 280, 200, 30);
        edit_from_station_jcb.setBounds(160, 320, 200, 30);
        edit_to_station_jcb.setBounds(550, 320, 200, 30);
        edit_departure_hh_txt.setBounds(185, 360, 30, 30);
        edit_departure_mm_txt.setBounds(245, 360, 30, 30);
        edit_departure_am_pm_jcb.setBounds(280, 360, 50, 30);
        edit_arrival_hh_txt.setBounds(575, 360, 30, 30);
        edit_arrival_mm_txt.setBounds(635, 360, 30, 30);
        edit_arrival_am_pm_jcb.setBounds(670, 360, 50, 30);
        edit_train_name_jcb.setBounds(160, 400, 200, 30);
        edit_train_number_jcb.setBounds(550, 400, 200, 30);
        edit_no_of_seats_txt.setBounds(160, 440, 200, 30);
        edit_submit_jbtn.setBounds(300, 550, 250, 35);

        // Edit Layout - attr
        edit_jpa.setLayout(null);

        // Adding all coponents to Edit Panel
        edit_jpa.add(booking_details_lbl);
        edit_jpa.add(fname_lbl);
        edit_jpa.add(edit_fname_txt);
        edit_jpa.add(lname_lbl);
        edit_jpa.add(edit_lname_txt);
        edit_jpa.add(phoneNum_lbl);
        edit_jpa.add(edit_phoneNum_txt);
        edit_jpa.add(gender_lbl);
        edit_jpa.add(edit_gender_jcb);
        edit_jpa.add(passenger_details_lbl);
        edit_jpa.add(title_lbl);
        edit_jpa.add(edit_title_jcb);
        edit_jpa.add(class_lbl);
        edit_jpa.add(edit_class_jcb);
        edit_jpa.add(from_station_lbl);
        edit_jpa.add(edit_from_station_jcb);
        edit_jpa.add(to_station_lbl);
        edit_jpa.add(edit_to_station_jcb);
        edit_jpa.add(departure_lbl);
        edit_jpa.add(departure_hh_lbl);
        edit_jpa.add(edit_departure_hh_txt);
        edit_jpa.add(departure_mm_lbl);
        edit_jpa.add(edit_departure_mm_txt);
        edit_jpa.add(edit_departure_am_pm_jcb);
        edit_jpa.add(arrival_lbl);
        edit_jpa.add(arrival_hh_lbl);
        edit_jpa.add(edit_arrival_hh_txt);
        edit_jpa.add(arrival_mm_lbl);
        edit_jpa.add(edit_arrival_mm_txt);
        edit_jpa.add(edit_arrival_am_pm_jcb);
        edit_jpa.add(train_name_lbl);
        edit_jpa.add(edit_train_name_jcb);
        edit_jpa.add(train_number_lbl);
        edit_jpa.add(edit_train_number_jcb);
        edit_jpa.add(no_of_seats_lbl);
        edit_jpa.add(edit_no_of_seats_txt);
        edit_jpa.add(edit_submit_jbtn);

        // Adding Edit Panel to Edit Frame
        EditFrame.add(edit_jpa);

        EditFrame.setSize(850, 650);
        EditFrame.setTitle("Edit Ticket");
        EditFrame.setVisible(true);
        EditFrame.setLocationRelativeTo(null);
        EditFrame.setResizable(false);
        EditFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/railway/images/Icon 3.PNG")));
        EditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void enablingContents() {
        save_jbtn.setEnabled(connectedToDB);
        clear_jbtn.setEnabled(connectedToDB);
        submit_jbtn.setEnabled(connectedToDB);
        home_refresh_jbtn.setEnabled(connectedToDB);
        bookings_reload_jbtn.setEnabled(connectedToDB);
        stations_reload_jbtn.setEnabled(connectedToDB);
        trains_reload_jbtn.setEnabled(connectedToDB);
        create_station_jbtn.setEnabled(connectedToDB);
        create_train_jbtn.setEnabled(connectedToDB);
        search_jbtn.setEnabled(connectedToDB);
        filter_jcb.setEnabled(connectedToDB);
        search_txt.setEnabled(connectedToDB);
        fname_txt.setEnabled(connectedToDB);
        lname_txt.setEnabled(connectedToDB);
        phoneNum_txt.setEnabled(connectedToDB);
        gender_jcb.setEnabled(connectedToDB);
        title_jcb.setEnabled(connectedToDB);
        class_jcb.setEnabled(connectedToDB);
        from_station_jcb.setEnabled(connectedToDB);
        to_station_jcb.setEnabled(connectedToDB);
        departure_hh_txt.setEnabled(connectedToDB);
        departure_mm_txt.setEnabled(connectedToDB);
        departure_am_pm_jcb.setEnabled(connectedToDB);
        arrival_hh_txt.setEnabled(connectedToDB);
        arrival_mm_txt.setEnabled(connectedToDB);
        arrival_am_pm_jcb.setEnabled(connectedToDB);
        train_name_jcb.setEnabled(connectedToDB);
        train_number_jcb.setEnabled(connectedToDB);
        no_of_seats_txt.setEnabled(connectedToDB);
        add_station_name_txt.setEnabled(connectedToDB);
        add_station_type_txt.setEnabled(connectedToDB);
        add_train_name_txt.setEnabled(connectedToDB);
        add_train_number_txt.setEnabled(connectedToDB);
    }

    // Awating connection status
    private void awaitingConn() {
        home_con_status_lbl.setText("Awaiting Connection ...");
        home_con_status_lbl.setForeground(Color.YELLOW);
        home_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

        book_con_status_lbl.setText("Awaiting Connection ...");
        book_con_status_lbl.setForeground(Color.YELLOW);
        book_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

        bookings_con_status_lbl.setText("Awaiting Connection ...");
        bookings_con_status_lbl.setForeground(Color.YELLOW);
        bookings_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

        add_station_con_status_lbl.setText("Awaiting Connection ...");
        add_station_con_status_lbl.setForeground(Color.YELLOW);
        add_station_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

        stations_con_status_lbl.setText("Awaiting Connection ...");
        stations_con_status_lbl.setForeground(Color.YELLOW);
        stations_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

        add_train_con_status_lbl.setText("Awaiting Connection ...");
        add_train_con_status_lbl.setForeground(Color.YELLOW);
        add_train_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

        trains_con_status_lbl.setText("Awaiting Connection ...");
        trains_con_status_lbl.setForeground(Color.YELLOW);
        trains_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

        about_con_status_lbl.setText("Awaiting Connection ...");
        about_con_status_lbl.setForeground(Color.YELLOW);
        about_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

        enablingContents();
    }

    // Setting up SQL Connection
    private void setConn() {
        // Setup SQL connection
        try {

            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway?zeroDateTimeBehavior=convertToNull", "root", "");
            stmt = con.createStatement();

            trains.clear();
            dtm_trains.setRowCount(0);
            dtm_stations.setRowCount(0);
            dtm_bookings.setRowCount(0);

            count_trains_lbl.setText(null);
            count_stations_lbl.setText(null);
            count_bookings_lbl.setText(null);

            from_station_jcb.removeAllItems();
            to_station_jcb.removeAllItems();
            train_name_jcb.removeAllItems();
            train_number_jcb.removeAllItems();
            connectedToDB = true;

            enablingContents();

            home_con_status_lbl.setText("Connected ...");
            home_con_status_lbl.setForeground(Color.GREEN);
            home_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

            book_con_status_lbl.setText("Connected ...");
            book_con_status_lbl.setForeground(Color.GREEN);
            book_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

            bookings_con_status_lbl.setText("Connected ...");
            bookings_con_status_lbl.setForeground(Color.GREEN);
            bookings_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

            add_station_con_status_lbl.setText("Connected ...");
            add_station_con_status_lbl.setForeground(Color.GREEN);
            add_station_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

            stations_con_status_lbl.setText("Connected ...");
            stations_con_status_lbl.setForeground(Color.GREEN);
            stations_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

            add_train_con_status_lbl.setText("Connected ...");
            add_train_con_status_lbl.setForeground(Color.GREEN);
            add_train_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

            trains_con_status_lbl.setText("Connected ...");
            trains_con_status_lbl.setForeground(Color.GREEN);
            trains_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

            about_con_status_lbl.setText("Connected ...");
            about_con_status_lbl.setForeground(Color.GREEN);
            about_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 20));

        } catch (IllegalStateException ex) {
            connectedToDB = false;
            trains.clear();
            dtm_trains.setRowCount(0);
            dtm_stations.setRowCount(0);
            dtm_bookings.setRowCount(0);

            count_trains_lbl.setText(null);
            count_stations_lbl.setText(null);
            count_bookings_lbl.setText(null);

            from_station_jcb.removeAllItems();
            to_station_jcb.removeAllItems();
            train_name_jcb.removeAllItems();
            train_number_jcb.removeAllItems();

            enablingContents();

            home_con_status_lbl.setText("Error : No connection to database");
            home_con_status_lbl.setForeground(Color.red);
            home_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            book_con_status_lbl.setText("Error : No connection to database");
            book_con_status_lbl.setForeground(Color.red);
            book_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            bookings_con_status_lbl.setText("Error : No connection to database");
            bookings_con_status_lbl.setForeground(Color.red);
            bookings_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            add_station_con_status_lbl.setText("Error : No connection to database");
            add_station_con_status_lbl.setForeground(Color.red);
            add_station_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            stations_con_status_lbl.setText("Error : No connection to database");
            stations_con_status_lbl.setForeground(Color.red);
            stations_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            add_train_con_status_lbl.setText("Error : No connection to database");
            add_train_con_status_lbl.setForeground(Color.red);
            add_train_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            trains_con_status_lbl.setText("Error : No connection to database");
            trains_con_status_lbl.setForeground(Color.red);
            trains_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            about_con_status_lbl.setText("Error : No connection to database");
            about_con_status_lbl.setForeground(Color.red);
            about_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

        } catch (Exception e) {
            connectedToDB = false;
            trains.clear();
            dtm_trains.setRowCount(0);
            dtm_stations.setRowCount(0);
            dtm_bookings.setRowCount(0);

            count_trains_lbl.setText(null);
            count_stations_lbl.setText(null);
            count_bookings_lbl.setText(null);

            from_station_jcb.removeAllItems();
            to_station_jcb.removeAllItems();
            train_name_jcb.removeAllItems();
            train_number_jcb.removeAllItems();

            enablingContents();

            home_con_status_lbl.setText("Error : No connection to db");
            home_con_status_lbl.setForeground(Color.red);
            home_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            book_con_status_lbl.setText("Error : No connection to db");
            book_con_status_lbl.setForeground(Color.red);
            book_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            bookings_con_status_lbl.setText("Error : No connection to db");
            bookings_con_status_lbl.setForeground(Color.red);
            bookings_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            add_station_con_status_lbl.setText("Error : No connection to db");
            add_station_con_status_lbl.setForeground(Color.red);
            add_station_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            stations_con_status_lbl.setText("Error : No connection to db");
            stations_con_status_lbl.setForeground(Color.red);
            stations_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            add_train_con_status_lbl.setText("Error : No connection to db");
            add_train_con_status_lbl.setForeground(Color.red);
            add_train_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            trains_con_status_lbl.setText("Error : No connection to db");
            trains_con_status_lbl.setForeground(Color.red);
            trains_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));

            about_con_status_lbl.setText("Error : No connection to db");
            about_con_status_lbl.setForeground(Color.red);
            about_con_status_lbl.setFont(new Font("Serif", Font.PLAIN, 17));
        }
    }

    // Validating Hours in time
    private String hh_check(String hh) {

        String HH = hh;

        boolean HH_Boolean_1;
//        boolean HH_Boolean_2;
        boolean HH_Boolean_3;

        Pattern HH_pattern = Pattern.compile("[0-1][0-2]");
//        Pattern HH_pattern_2 = Pattern.compile("[2][0-3]");
        Pattern HH_pattern_3 = Pattern.compile("[1-9]");

        Matcher HH_matcher = HH_pattern.matcher(HH);
//        Matcher HH_matcher_2 = HH_pattern_2.matcher(HH);
        Matcher HH_matcher_3 = HH_pattern_3.matcher(HH);

        HH_Boolean_1 = HH_matcher.matches();
//        HH_Boolean_2 = HH_matcher_2.matches();
        HH_Boolean_3 = HH_matcher_3.matches();

        if (HH_Boolean_1 || HH_Boolean_3) {
            return HH;
        } else {
            return "hh_error";
        }

    }

    // Validating Mins in time
    private String mm_check(String mm) {

        String MM = mm;

        boolean MM_Boolean;

        Pattern MM_pattern = Pattern.compile("[0-5][0-9]");

        Matcher MM_matcher = MM_pattern.matcher(MM);

        MM_Boolean = MM_matcher.matches();

        if (MM_Boolean) {
            return MM;
        } else {
            return "mm_error";
        }

    }

    // Checking for days of the week
    private String day_of_week(String day) {
        String day_of_week = day;

        int date = Integer.parseInt(day_of_week);
        switch (date) {
            case 0:
                day_of_week = "Sun";
                break;
            case 1:
                day_of_week = "Mon";
                break;
            case 2:
                day_of_week = "Tues";
                break;
            case 3:
                day_of_week = "Weds";
                break;
            case 4:
                day_of_week = "Thurs";
                break;
            case 5:
                day_of_week = "Fri";
                break;
            case 6:
                day_of_week = "Sat";
                break;
        }

        return day_of_week;
    }

    // Checking for months of the year
    private String month_of_year(String month) {
        String month_of_year = month;

        int date = Integer.parseInt(month_of_year);
        switch (date) {
            case 0:
                month_of_year = "January";
                break;
            case 1:
                month_of_year = "February";
                break;
            case 2:
                month_of_year = "March";
                break;
            case 3:
                month_of_year = "April";
                break;
            case 4:
                month_of_year = "May";
                break;
            case 5:
                month_of_year = "June";
                break;
            case 6:
                month_of_year = "July";
                break;
            case 7:
                month_of_year = "August";
                break;
            case 8:
                month_of_year = "September";
                break;
            case 9:
                month_of_year = "October";
                break;
            case 10:
                month_of_year = "November";
                break;
            case 11:
                month_of_year = "December";
                break;
        }

        return month_of_year;
    }

    // Validating Seats
    private String seat_check(String seat) {

        String Seats = seat;

        boolean Seat_Boolean;
        boolean Seat_Boolean_2;

        Pattern Seat_pattern = Pattern.compile("[0-9]");
        Pattern Seat_pattern_2 = Pattern.compile("[0-9][0]");

        Matcher Seat_matcher = Seat_pattern.matcher(Seats);
        Matcher Seat_matcher_2 = Seat_pattern_2.matcher(Seats);

        Seat_Boolean = Seat_matcher.matches();
        Seat_Boolean_2 = Seat_matcher_2.matches();

        if (Seat_Boolean || Seat_Boolean_2) {
            return Seats;
        } else {
            return "seat_error";
        }

    }

    // Validating Phone numbers
    private String phone_num_check(String phone_num) {

        String mobile = phone_num;

        boolean phoneBoolean1;
        boolean phoneBoolean2;

        Pattern phonePattern1 = Pattern.compile("[2][3][4][7-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
        Pattern phonePattern2 = Pattern.compile("[0][7-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");

        Matcher phoneMatcher1 = phonePattern1.matcher(mobile);
        Matcher phoneMatcher2 = phonePattern2.matcher(mobile);

        phoneBoolean1 = phoneMatcher1.matches();
        phoneBoolean2 = phoneMatcher2.matches();

        if (phoneBoolean1 || phoneBoolean2) {
            return mobile;
        } else {
            return "phone_error";
        }
    }

    // Validating Departure HH and MM
    private String check_departure_hh_mm(String hh, String mm) {

        String HH = hh_check(hh);
        String MM = mm_check(mm);

        if (HH.equals("hh_error") || MM.equals("mm_error")) {
            return "error";
        } else {
            String time = HH + ":" + MM;
            return time;
        }

    }

    // Validating Arrival HH and MM
    private String check_arrival_hh_mm(String hh, String mm) {

        String HH = hh_check(hh);
        String MM = mm_check(mm);

        if (HH.equals("hh_error") || MM.equals("mm_error")) {
            return "error";
        } else {
            String time = HH + ":" + MM;
            return time;
        }

    }

    // Validating Ticket number
    private void check_ticket_number() {
        synchronized (this) {
            try {
                String str = "SELECT * FROM bookings";

                stmt = con.createStatement();

                rs = stmt.executeQuery(str);

                while (rs.next()) {
                    String hi = rs.getString("ticketNumber");

                    if (rs.isLast()) {
                        int joker = Integer.parseInt(hi) + 3;
                        num = joker;
                    } else {
                        int joker = 1000;
                        num = joker;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainFrame, "SQL Error : " + ex.getMessage() + "\n Code : " + ex.getErrorCode(), "Warning Message", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MainFrame, "Error : " + ex.getMessage(), "Warning Message", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // Validating Station Type
    private String check_station_type(String type) {
        String sta_type = type.toUpperCase();

        boolean station_type_Boolean;
        boolean station_type_Boolean_2;

        Pattern station_type_Pattern = Pattern.compile("[H][A][L][T]");
        Pattern station_type_Pattern_2 = Pattern.compile("[T][E][R][M][I][N][A][L]");

        Matcher station_type_Matcher = station_type_Pattern.matcher(sta_type);
        Matcher station_type_Matcher_2 = station_type_Pattern_2.matcher(sta_type);

        station_type_Boolean = station_type_Matcher.matches();
        station_type_Boolean_2 = station_type_Matcher_2.matches();

        if (station_type_Boolean || station_type_Boolean_2) {
            return sta_type;
        } else {
            return "type_error";
        }
    }

    // Checking for already added stations before adding new ones
    private String check_for_stations(String station) {
        String the_station = station;

        synchronized (this) {
            try {
                String str = "SELECT * FROM stations";

                stmt = con.createStatement();
                rs = stmt.executeQuery(str);

                while (rs.next()) {
                    String station_name = rs.getString("station_name");
                    if (the_station.equalsIgnoreCase(station_name)) {
                        the_station = "station_exists";
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
        return the_station;
    }

    // Checking for already added trains before adding new ones
    private String check_for_trains(String train_num) {
        String the_train = train_num;

        synchronized (this) {
            try {
                String str = "SELECT * FROM trains";

                stmt = con.createStatement();
                rs = stmt.executeQuery(str);

                while (rs.next()) {
                    String train_number = rs.getString("train_number");
                    if (the_train.equals(train_number)) {
                        the_train = "train_exists";
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
        return the_train;
    }

    // Update a value In Database
    private String updateValue(String fname, String lname, String mobile, String gender,
            String title, String class_, String seat, String from, String to, String depart,
            String arrive, String t_name, String t_number, String added_date, String tNum) {

        String response = "";
        synchronized (this) {
            try {
                String str = "UPDATE bookings SET firstName = ?, lastName = ?, phoneNumber = ?, gender = ?, title = ?, class = ?, seat = ?, from_station = ?, to_station = ?, departure_time = ?, arrival_time = ?, train_name = ?, train_number = ?, added_date = ? WHERE ticketNumber = ? ";

                stat = con.prepareStatement(str);

                stat.setString(1, fname);
                stat.setString(2, lname);
                stat.setString(3, mobile);
                stat.setString(4, gender);
                stat.setString(5, title);
                stat.setString(6, class_);
                stat.setString(7, seat);
                stat.setString(8, from);
                stat.setString(9, to);
                stat.setString(10, depart);
                stat.setString(11, arrive);
                stat.setString(12, t_name);
                stat.setString(13, t_number);
                stat.setString(14, added_date);
                stat.setString(15, tNum);

                stat.execute();

                response = "Successful";

                // Clears the current parameter values immediately.
                stat.clearParameters();
            } catch (SQLException ex) {
                System.out.println(ex);
                response = "Error Occured";
            } catch (Exception e) {
                System.out.println(e);
                response = "Error Occured";
            }
        }
        return response;
    }

    // Home refresh action
    private void refreshHome() {
        showTrainsCounts();
        showStationsCounts();
        showBookingsCounts();
    }

    // Submit ticket action
    private void submit() {
        synchronized (this) {
            String mobile = phone_num_check(phoneNum_txt.getText());

            String departure_time = check_departure_hh_mm(departure_hh_txt.getText(), departure_mm_txt.getText());
            String arrival_time = check_arrival_hh_mm(arrival_hh_txt.getText(), arrival_mm_txt.getText());

            String seat = seat_check(no_of_seats_txt.getText());

            if (fname_txt.getText().equals("") || lname_txt.getText().equals("") || phoneNum_txt.getText().equals("")
                    || no_of_seats_txt.getText().equals("") || departure_hh_txt.getText().equals("") || departure_mm_txt.getText().equals("")
                    || arrival_hh_txt.getText().equals("") || arrival_mm_txt.getText().equals("")) {
                JOptionPane.showMessageDialog(MainFrame, "Fill in all fields", "Warning message", JOptionPane.WARNING_MESSAGE);
            } else {
                if (mobile.equals("phone_error")) {
                    JOptionPane.showMessageDialog(MainFrame, "Valid phone number format[ +234XXXXXXXXXX ] and [ 0X0XXXXXXXX ]", "Warning message", JOptionPane.WARNING_MESSAGE);
                } else if (departure_time.equals("error") || arrival_time.equals("error")) {
                    JOptionPane.showMessageDialog(MainFrame, "Valid Departure / Arrival time format - [ 12HR ]", "Warning message", JOptionPane.WARNING_MESSAGE);
                } else if (seat.equals("seat_error")) {
                    JOptionPane.showMessageDialog(MainFrame, "Seat(s) must be more than 1 and not exceed 10", "Warning message", JOptionPane.WARNING_MESSAGE);
                } else if (from_station_jcb.getSelectedIndex() == to_station_jcb.getSelectedIndex()) {
                    JOptionPane.showMessageDialog(MainFrame, "Change either station, action not allowed !", "Warning message", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (dlm_book.getSize() == 10) {
                        JOptionPane.showMessageDialog(MainFrame, "Reached maximum, save current list or clear to continue", "Warning message", JOptionPane.WARNING_MESSAGE);
                    } else {

                        String fname = fname_txt.getText();
                        String lname = lname_txt.getText();
                        String gender = gender_jcb.getSelectedItem().toString();
                        String title = title_jcb.getSelectedItem().toString();
                        String class_ = class_jcb.getSelectedItem().toString();
                        String from = from_station_jcb.getSelectedItem().toString();
                        String to = to_station_jcb.getSelectedItem().toString();
                        String depart_time = departure_time + " " + departure_am_pm_jcb.getSelectedItem().toString();
                        String arrive_time = arrival_time + " " + arrival_am_pm_jcb.getSelectedItem().toString();
                        String t_name = train_name_jcb.getSelectedItem().toString();
                        String t_number = train_number_jcb.getSelectedItem().toString();

                        Booking book = new Booking();
                        book.setFname(fname);
                        book.setLname(lname);
                        book.setMobile(mobile);
                        book.setGender(gender);
                        book.setTitle(title);
                        book.setClass_(class_);
                        book.setFrom(from);
                        book.setTo(to);
                        book.setDepart(depart_time);
                        book.setArrive(arrive_time);
                        book.setTname(t_name);
                        book.setTnumber(t_number);
                        book.setSeat(seat);

                        bookingList.add(book);

                        ArrayList<Booking> tempList = new ArrayList<Booking>(bookingList);

                        dlm_book.removeAllElements();

                        for (Booking b : tempList) {
                            dlm_book.addElement(b);
                        }

                        fnames.add(fname);
                        lnames.add(lname);
                        phoneNums.add(mobile);
                        genders.add(gender);
                        titles.add(title);
                        classes.add(class_);
                        seats.add(seat);
                        from_stations.add(from);
                        to_stations.add(to);
                        departures.add(depart_time);
                        arrivals.add(arrive_time);
                        train_names.add(t_name);
                        train_numbers.add(t_number);

                        fname_txt.setText(null);
                        lname_txt.setText(null);
                        phoneNum_txt.setText(null);
                        gender_jcb.setSelectedIndex(0);
                        title_jcb.setSelectedIndex(0);
                        class_jcb.setSelectedIndex(0);
                        no_of_seats_txt.setText(null);
                        from_station_jcb.setSelectedIndex(0);
                        to_station_jcb.setSelectedIndex(0);
                        departure_hh_txt.setText(null);
                        departure_mm_txt.setText("00");
                        departure_am_pm_jcb.setSelectedIndex(0);
                        arrival_hh_txt.setText(null);
                        arrival_mm_txt.setText("00");
                        arrival_am_pm_jcb.setSelectedIndex(0);
                        train_name_jcb.setSelectedIndex(0);
                        train_number_jcb.setSelectedIndex(0);

                    }
                }
            }
        }
    }

    // Save ticket to DB action
    private void save() {
        if (connectedToDB) {
            if (dlm_book.isEmpty()) {
                JOptionPane.showMessageDialog(MainFrame, "No Bookings found in list", "Error message", JOptionPane.ERROR_MESSAGE);
            } else {
                synchronized (this) {
                    try {
                        Iterator fname_itr = fnames.iterator();
                        Iterator lname_itr = lnames.iterator();
                        Iterator phoneNum_itr = phoneNums.iterator();
                        Iterator gender_itr = genders.iterator();
                        Iterator title_itr = titles.iterator();
                        Iterator class_itr = classes.iterator();
                        Iterator seat_itr = seats.iterator();
                        Iterator from_station_itr = from_stations.iterator();
                        Iterator to_station_itr = to_stations.iterator();
                        Iterator departure_itr = departures.iterator();
                        Iterator arrival_itr = arrivals.iterator();
                        Iterator train_name_itr = train_names.iterator();
                        Iterator train_number_itr = train_numbers.iterator();

                        while (fname_itr.hasNext()) {
                            while (lname_itr.hasNext()) {
                                while (phoneNum_itr.hasNext()) {
                                    while (gender_itr.hasNext()) {
                                        while (title_itr.hasNext()) {
                                            while (class_itr.hasNext()) {
                                                while (seat_itr.hasNext()) {
                                                    while (from_station_itr.hasNext()) {
                                                        while (to_station_itr.hasNext()) {
                                                            while (departure_itr.hasNext()) {
                                                                while (arrival_itr.hasNext()) {
                                                                    while (train_name_itr.hasNext()) {
                                                                        while (train_number_itr.hasNext()) {
                                                                            check_ticket_number();

                                                                            String fname = fname_itr.next().toString();
                                                                            String lname = lname_itr.next().toString();
                                                                            String phone = phoneNum_itr.next().toString();
                                                                            String gender = gender_itr.next().toString();
                                                                            String title = title_itr.next().toString();
                                                                            String class_ = class_itr.next().toString();
                                                                            String seat = seat_itr.next().toString();
                                                                            String from = from_station_itr.next().toString();
                                                                            String to = to_station_itr.next().toString();
                                                                            String departure = departure_itr.next().toString();
                                                                            String arrival = arrival_itr.next().toString();
                                                                            String train_name = train_name_itr.next().toString();
                                                                            String train_number = train_number_itr.next().toString();
                                                                            String ticket_number = Integer.toString(num);

                                                                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                                                                            Date dateN = new Date();
                                                                            String Date = dateFormat.format(dateN);

                                                                            stat = con.prepareStatement("INSERT INTO bookings (ticketNumber, firstName, "
                                                                                    + "lastName, phoneNumber, gender, title, class, seat, from_station, "
                                                                                    + "to_station, departure_time, arrival_time, train_name, train_number, added_date) "
                                                                                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                                                                            stat.setString(1, ticket_number);
                                                                            stat.setString(2, fname);
                                                                            stat.setString(3, lname);
                                                                            stat.setString(4, phone);
                                                                            stat.setString(5, gender);
                                                                            stat.setString(6, title);
                                                                            stat.setString(7, class_);
                                                                            stat.setString(8, seat);
                                                                            stat.setString(9, from);
                                                                            stat.setString(10, to);
                                                                            stat.setString(11, departure);
                                                                            stat.setString(12, arrival);
                                                                            stat.setString(13, train_name);
                                                                            stat.setString(14, train_number);
                                                                            stat.setString(15, Date);

                                                                            stat.execute();

                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        int rows = fnames.size();

                        JOptionPane.showMessageDialog(MainFrame, "Saved successfully, " + rows + " row(s) inserted", "Success message", JOptionPane.PLAIN_MESSAGE);

                        dlm_book.clear();
                        fnames.clear();
                        lnames.clear();
                        phoneNums.clear();
                        genders.clear();
                        titles.clear();
                        classes.clear();
                        seats.clear();
                        from_stations.clear();
                        to_stations.clear();
                        departures.clear();
                        arrivals.clear();
                        train_names.clear();
                        train_numbers.clear();
                        t_nums.clear();

                    } catch (SQLException ex) {

                        String msg = ex.getMessage();

                        if (ex.getMessage().equals(msg)) {
                            JOptionPane.showMessageDialog(MainFrame, "SQL Error : " + msg + "\n Code : " + ex.getErrorCode(), "Warning Message", JOptionPane.WARNING_MESSAGE);

                        }

                    } catch (Exception e) {
                        String msg = e.getLocalizedMessage();
                        JOptionPane.showMessageDialog(MainFrame, "Error : " + msg, "Warning Message", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        } else {
            JOptionPane.showMessageDialog(MainFrame, "No connection to database", "Error message", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Clear Booking fields
    private void clear() {
        synchronized (this) {
            if (!dlm_book.isEmpty()) {
                int dialog = JOptionPane.showConfirmDialog(MainFrame, "Do you really want to clear list ?", "Question message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (dialog == 0) {
                    dlm_book.clear();
                    fnames.clear();
                    lnames.clear();
                    phoneNums.clear();
                    genders.clear();
                    titles.clear();
                    classes.clear();
                    seats.clear();
                    from_stations.clear();
                    to_stations.clear();
                    departures.clear();
                    arrivals.clear();
                    train_names.clear();
                    train_numbers.clear();
                    t_nums.clear();
                }
            } else {
                JOptionPane.showMessageDialog(MainFrame, "Nothing to delete ...", "Information message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Save edit changes
    private void saveChanges() {
        if (connectedToDB) {
            synchronized (this) {
                String fname, lname, mobile, gender, title, class_, seat, from, depart, to, arrive, t_number, t_name;

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                Date dateN = new Date();
                String Date = dateFormat.format(dateN);

                mobile = phone_num_check(edit_phoneNum_txt.getText().trim());

                String departure_time = check_departure_hh_mm(edit_departure_hh_txt.getText().trim(), edit_departure_mm_txt.getText().trim());
                String arrival_time = check_arrival_hh_mm(edit_arrival_hh_txt.getText().trim(), edit_arrival_mm_txt.getText().trim());

                seat = seat_check(edit_no_of_seats_txt.getText().trim());

                fname = edit_fname_txt.getText();
                lname = edit_lname_txt.getText();
                gender = edit_gender_jcb.getSelectedItem().toString();
                title = edit_title_jcb.getSelectedItem().toString();
                class_ = edit_class_jcb.getSelectedItem().toString();
                from = edit_from_station_jcb.getSelectedItem().toString();
                to = edit_to_station_jcb.getSelectedItem().toString();
                depart = departure_time + " " + edit_departure_am_pm_jcb.getSelectedItem().toString();
                arrive = arrival_time + " " + edit_arrival_am_pm_jcb.getSelectedItem().toString();
                t_name = edit_train_name_jcb.getSelectedItem().toString();
                t_number = edit_train_number_jcb.getSelectedItem().toString();

                if (mobile.equals("phone_error")) {
                    JOptionPane.showMessageDialog(EditFrame, "Valid number format[ +234XXXXXXXXXX ] and [ 0X0XXXXXXXX ]", "Warning message", JOptionPane.WARNING_MESSAGE);
                } else if (departure_time.equals("error") || arrival_time.equals("error")) {
                    JOptionPane.showMessageDialog(EditFrame, "Valid Departure / Arrival time format - [ 12HR ]", "Warning message", JOptionPane.WARNING_MESSAGE);
                } else if (seat.equals("seat_error")) {
                    JOptionPane.showMessageDialog(EditFrame, "Seat(s) must be more than 1 and not exceed 10", "Warning message", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {

                        String response = "";
                        synchronized (this) {
                            response = updateValue(fname, lname, mobile, gender, title, class_, seat, from, to, depart, arrive, t_name, t_number, Date, global_tNum);
                        }
                        if (response.equals("Successful")) {
                            JOptionPane.showMessageDialog(EditFrame, "Changes Saved");
                            EditFrame.dispose();
                            reload_bookings();
                        } else {
                            JOptionPane.showMessageDialog(EditFrame, "An Error Occured", "Error Message", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(EditFrame, e, "Error Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(MainFrame, "No connection to database", "Error message", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Showing Home counts for Trains
    private void showTrainsCounts() {
        synchronized (this) {
            try {
                String str = "SELECT COUNT(*) FROM trains";

                stmt = con.createStatement();
                rs = stmt.executeQuery(str);

                while (rs.next()) {
                    int trainsCount = rs.getInt(1);
                    String str_count = String.valueOf(trainsCount);
                    if (connectedToDB) {
                        count_trains_lbl.setText(str_count);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Showing Home counts for Stations
    private void showStationsCounts() {
        synchronized (this) {
            try {
                String str = "SELECT COUNT(*) FROM stations";

                stmt = con.createStatement();
                rs = stmt.executeQuery(str);

                while (rs.next()) {
                    int stationsCount = rs.getInt(1);
                    String str_count = String.valueOf(stationsCount);
                    if (connectedToDB) {
                        count_stations_lbl.setText(str_count);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Showing Home counts for Bookings
    private void showBookingsCounts() {
        synchronized (this) {
            try {
                String str = "SELECT COUNT(*) FROM bookings";

                stmt = con.createStatement();
                rs = stmt.executeQuery(str);

                while (rs.next()) {
                    int bookingsCount = rs.getInt(1);
                    String str_count = String.valueOf(bookingsCount);
                    if (connectedToDB) {
                        count_bookings_lbl.setText(str_count);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Display all Bookings
    private void showBookings() {

        synchronized (this) {
            try {
                String str = "SELECT * FROM bookings ORDER BY added_date DESC";

                stmt = con.createStatement();
                rs = stmt.executeQuery(str);

                dtm_bookings.setRowCount(0);

                while (rs.next()) {
                    String t_no = rs.getString("ticketNumber");
                    String fname = rs.getString("firstName");
                    String lname = rs.getString("lastName");
                    String fullname = fname + " " + lname;
                    String mobile = "      " + rs.getString("phoneNumber") + "      ";
                    String gender = "        " + rs.getString("gender") + "        ";
                    String title = rs.getString("title");
                    String class_ = rs.getString("class");
                    String seat = rs.getString("seat") + " seat(s)";
                    String from = rs.getString("from_station");
                    String depart = "        " + rs.getString("departure_time") + "        ";
                    String to = rs.getString("to_station");
                    String arrive = "        " + rs.getString("arrival_time") + "        ";
                    String t_number = "          " + rs.getString("train_number") + "          ";
                    String t_name = rs.getString("train_name");
                    String date = rs.getString("added_date");

                    bookings_obj = new Object[]{
                        t_no, fullname, mobile, gender, title, class_, seat, from, depart, to, arrive, t_number, t_name, date
                    };

                    dtm_bookings.addRow(bookings_obj);
                    bookings_tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    for (int column = 0; column < bookings_tbl.getColumnCount(); column++) {
                        TableColumn tableColumn = bookings_tbl.getColumnModel().getColumn(column);
                        int preferredWidth = tableColumn.getMinWidth();
                        int maxWidth = tableColumn.getMaxWidth();

                        for (int row = 0; row < bookings_tbl.getRowCount(); row++) {
                            TableCellRenderer cellRenderer = bookings_tbl.getCellRenderer(row, column);
                            Component c = bookings_tbl.prepareRenderer(cellRenderer, row, column);
                            int width = c.getPreferredSize().width + bookings_tbl.getIntercellSpacing().width;
                            preferredWidth = Math.max(preferredWidth, width);

                            //  We've exceeded the maximum width, no need to check other rows
                            if (preferredWidth >= maxWidth) {
                                preferredWidth = maxWidth;
                                break;
                            }
                        }

                        tableColumn.setPreferredWidth(preferredWidth);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    // Reload all bookings to update values
    private void reload_bookings() {

        synchronized (this) {
            try {
                String str = "SELECT * FROM bookings ORDER BY added_date DESC";

                stmt = con.createStatement();
                rs = stmt.executeQuery(str);

                dtm_bookings.setRowCount(0);

                while (rs.next()) {
                    String t_no = rs.getString("ticketNumber");
                    String fname = rs.getString("firstName");
                    String lname = rs.getString("lastName");
                    String fullname = fname + " " + lname;
                    String mobile = "      " + rs.getString("phoneNumber") + "      ";
                    String gender = "        " + rs.getString("gender") + "        ";
                    String title = rs.getString("title");
                    String class_ = rs.getString("class");
                    String seat = rs.getString("seat") + " seat(s)";
                    String from = rs.getString("from_station");
                    String depart = "        " + rs.getString("departure_time") + "        ";
                    String to = rs.getString("to_station");
                    String arrive = "        " + rs.getString("arrival_time") + "        ";
                    String t_number = "          " + rs.getString("train_number") + "          ";
                    String t_name = rs.getString("train_name");
                    String date = rs.getString("added_date");

                    bookings_obj = new Object[]{
                        t_no, fullname, mobile, gender, title, class_, seat, from, depart, to, arrive, t_number, t_name, date
                    };

                    dtm_bookings.addRow(bookings_obj);
                    bookings_tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    for (int column = 0; column < bookings_tbl.getColumnCount(); column++) {
                        TableColumn tableColumn = bookings_tbl.getColumnModel().getColumn(column);
                        int preferredWidth = tableColumn.getMinWidth();
                        int maxWidth = tableColumn.getMaxWidth();

                        for (int row = 0; row < bookings_tbl.getRowCount(); row++) {
                            TableCellRenderer cellRenderer = bookings_tbl.getCellRenderer(row, column);
                            Component c = bookings_tbl.prepareRenderer(cellRenderer, row, column);
                            int width = c.getPreferredSize().width + bookings_tbl.getIntercellSpacing().width;
                            preferredWidth = Math.max(preferredWidth, width);

                            //  We've exceeded the maximum width, no need to check other rows
                            if (preferredWidth >= maxWidth) {
                                preferredWidth = maxWidth;
                                break;
                            }
                        }

                        tableColumn.setPreferredWidth(preferredWidth);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Display all Stations
    private void showStations() {
        synchronized (this) {
            try {
                String str = "SELECT * FROM stations";

                stmt = con.createStatement();
                rs = stmt.executeQuery(str);

                dtm_stations.setRowCount(0);

                while (rs.next()) {
                    String id = rs.getString("id");
                    String stationName = rs.getString("station_name");
                    String stationType = rs.getString("station_type");
                    String date = rs.getString("added_date");

                    Date dateN = new Date(date);

                    String the_date = String.valueOf(dateN.getDay());
                    String day_of_week = day_of_week(the_date);

                    String the_month = String.valueOf(dateN.getMonth());
                    String month_of_year = month_of_year(the_month);

                    int year = dateN.getYear() + 1900;

                    String full_date = day_of_week + " " + dateN.getDate() + ", " + month_of_year + ", " + year;

                    stations_obj = new Object[]{
                        id, stationName, stationType, full_date
                    };

                    dtm_stations.addRow(stations_obj);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Reload all stations to update values
    private void reload_stations() {
        synchronized (this) {
            try {
                String str = "SELECT * FROM stations";

                stmt = con.createStatement();
                rs = stmt.executeQuery(str);

                dtm_stations.setRowCount(0);

                while (rs.next()) {
                    String id = rs.getString("id");
                    String stationName = rs.getString("station_name");
                    String stationType = rs.getString("station_type");
                    String date = rs.getString("added_date");

                    Date dateN = new Date(date);

                    String the_date = String.valueOf(dateN.getDay());
                    String day_of_week = day_of_week(the_date);

                    String the_month = String.valueOf(dateN.getMonth());
                    String month_of_year = month_of_year(the_month);

                    int year = dateN.getYear() + 1900;

                    String full_date = day_of_week + " " + dateN.getDate() + ", " + month_of_year + ", " + year;

                    stations_obj = new Object[]{
                        id, stationName, stationType, full_date
                    };

                    dtm_stations.addRow(stations_obj);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Display all Trains
    private void showTrains() {
        synchronized (this) {
            try {
                String str = "SELECT * FROM trains";

                stmt = con.createStatement();
                rs = stmt.executeQuery(str);

                dtm_trains.setRowCount(0);

                while (rs.next()) {
                    String id = rs.getString("id");
                    String trainName = rs.getString("train_name");
                    String trainNumber = rs.getString("train_number");
                    String date = rs.getString("added_date");

                    Date dateN = new Date(date);

                    String the_date = String.valueOf(dateN.getDay());
                    String day_of_week = day_of_week(the_date);

                    String the_month = String.valueOf(dateN.getMonth());
                    String month_of_year = month_of_year(the_month);

                    int year = dateN.getYear() + 1900;

                    String full_date = day_of_week + " " + dateN.getDate() + ", " + month_of_year + ", " + year;

                    trains_obj = new Object[]{
                        id, trainName, trainNumber, full_date
                    };

                    dtm_trains.addRow(trains_obj);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Reload all trains to update values
    private void reload_trains() {
        synchronized (this) {
            try {
                String str = "SELECT * FROM trains";

                stmt = con.createStatement();
                rs = stmt.executeQuery(str);

                dtm_trains.setRowCount(0);

                while (rs.next()) {
                    String id = rs.getString("id");
                    String trainName = rs.getString("train_name");
                    String trainNumber = rs.getString("train_number");
                    String date = rs.getString("added_date");

                    Date dateN = new Date(date);

                    String the_date = String.valueOf(dateN.getDay());
                    String day_of_week = day_of_week(the_date);

                    String the_month = String.valueOf(dateN.getMonth());
                    String month_of_year = month_of_year(the_month);

                    int year = dateN.getYear() + 1900;

                    String full_date = day_of_week + " " + dateN.getDate() + ", " + month_of_year + ", " + year;

                    trains_obj = new Object[]{
                        id, trainName, trainNumber, full_date
                    };

                    dtm_trains.addRow(trains_obj);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Creating new stations
    private void create_station() {
        if (connectedToDB) {

            synchronized (this) {
                String station = check_for_stations(add_station_name_txt.getText());
                String type = check_station_type(add_station_type_txt.getText());
                try {
                    if (add_station_name_txt.getText().equals("") || add_station_type_txt.getText().equals("")) {
                        JOptionPane.showMessageDialog(MainFrame, "Fill in all fields", "Warning Message", JOptionPane.WARNING_MESSAGE);
                    } else {

                        if (station.equals("station_exists")) {
                            JOptionPane.showMessageDialog(MainFrame, "Station already exists, try something else", "Error Message", JOptionPane.ERROR_MESSAGE);
                        } else {

                            if (type.equals("type_error")) {
                                JOptionPane.showMessageDialog(MainFrame, "Incorrect Station Type", "Error Message", JOptionPane.ERROR_MESSAGE);
                            } else {

                                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                                Date dateN = new Date();
                                String Date = dateFormat.format(dateN);

                                String station_name = station;
                                String station_type = type;

                                stat = con.prepareStatement("INSERT INTO stations (station_name, station_type, added_date) VALUES(?, ?, ?)");

                                stat.setString(1, station_name);
                                stat.setString(2, station_type);
                                stat.setString(3, Date);

                                stat.execute();

                                JOptionPane.showMessageDialog(MainFrame, "Saved successfully", "Success message", JOptionPane.PLAIN_MESSAGE);
                                add_station_name_txt.setText(null);
                                add_station_type_txt.setText(null);
                                Tabs.setSelectedComponent(Tabs.getComponentAt(4));
                            }
                        }
                    }
                } catch (SQLException ex) {

                    String msg = ex.getMessage();

                    if (ex.getMessage().equals(msg)) {
                        JOptionPane.showMessageDialog(MainFrame, "SQL Error : " + msg + "\n Code : " + ex.getErrorCode(), "Warning Message", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception e) {
                    String msg = e.getLocalizedMessage();
                    JOptionPane.showMessageDialog(MainFrame, "Error : " + msg, "Warning Message", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(MainFrame, "No connection to database", "Error message", JOptionPane.ERROR_MESSAGE);
        }

    }

    // Creating new trains
    private void create_train() {
        if (connectedToDB) {

            synchronized (this) {
                String train_number = check_for_trains(add_train_number_txt.getText());
                try {
                    if (add_train_name_txt.getText().equals("") || add_train_number_txt.getText().equals("")) {
                        JOptionPane.showMessageDialog(MainFrame, "Fill in all fields", "Warning Message", JOptionPane.WARNING_MESSAGE);
                    } else {

                        if (train_number.equals("train_exists")) {
                            JOptionPane.showMessageDialog(MainFrame, "Train already exists and is unique, try something else", "Error Message", JOptionPane.ERROR_MESSAGE);
                        } else {

                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                            Date dateN = new Date();
                            String Date = dateFormat.format(dateN);

                            String train_name = add_train_name_txt.getText();

                            stat = con.prepareStatement("INSERT INTO trains (train_name, train_number, added_date) VALUES(?, ?, ?)");

                            stat.setString(1, train_name);
                            stat.setString(2, train_number);
                            stat.setString(3, Date);

                            stat.execute();

                            JOptionPane.showMessageDialog(MainFrame, "Saved successfully", "Success message", JOptionPane.PLAIN_MESSAGE);
                            add_train_name_txt.setText(null);
                            add_train_number_txt.setText(null);
                            Tabs.setSelectedComponent(Tabs.getComponentAt(6));
                        }
                    }
                } catch (SQLException ex) {

                    String msg = ex.getMessage();

                    if (ex.getMessage().equals(msg)) {
                        JOptionPane.showMessageDialog(MainFrame, "SQL Error : " + msg + "\n Code : " + ex.getErrorCode(), "Warning Message", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception e) {
                    String msg = e.getLocalizedMessage();
                    JOptionPane.showMessageDialog(MainFrame, "Error : " + msg, "Warning Message", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(MainFrame, "No connection to database", "Error message", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Search button action
    private void search() {
        synchronized (this) {
            try {
                String str = "SELECT * FROM bookings WHERE ticketNumber LIKE ? OR firstName LIKE ? OR train_number LIKE ? OR from_station LIKE ? ORDER BY added_date DESC";

                stat = con.prepareStatement(str);

                String word = search_txt.getText();
                String search_word = "%" + word + "%";

                stat.setString(1, search_word);
                stat.setString(2, search_word);
                stat.setString(3, search_word);
                stat.setString(4, search_word);

                ResultSet rs = stat.executeQuery();

                dtm_bookings.setRowCount(0);

                if (!(rs.next())) {
                    JOptionPane.showMessageDialog(MainFrame, "No Search results for " + word);
                } else {

                    while (rs.next()) {
                        String t_no = rs.getString("ticketNumber");
                        String fname = rs.getString("firstName");
                        String lname = rs.getString("lastName");
                        String fullname = fname + " " + lname;
                        String mobile = "      " + rs.getString("phoneNumber") + "      ";
                        String gender = "        " + rs.getString("gender") + "        ";
                        String title = rs.getString("title");
                        String class_ = rs.getString("class");
                        String seat = rs.getString("seat") + " seat(s)";
                        String from = rs.getString("from_station");
                        String depart = "        " + rs.getString("departure_time") + "        ";
                        String to = rs.getString("to_station");
                        String arrive = "        " + rs.getString("arrival_time") + "        ";
                        String t_number = "          " + rs.getString("train_number") + "          ";
                        String t_name = rs.getString("train_name");
                        String date = rs.getString("added_date");

                        bookings_obj = new Object[]{
                            t_no, fullname, mobile, gender, title, class_, seat, from, depart, to, arrive, t_number, t_name, date
                        };

                        dtm_bookings.addRow(bookings_obj);
                        bookings_tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        for (int column = 0; column < bookings_tbl.getColumnCount(); column++) {
                            TableColumn tableColumn = bookings_tbl.getColumnModel().getColumn(column);
                            int preferredWidth = tableColumn.getMinWidth();
                            int maxWidth = tableColumn.getMaxWidth();

                            for (int row = 0; row < bookings_tbl.getRowCount(); row++) {
                                TableCellRenderer cellRenderer = bookings_tbl.getCellRenderer(row, column);
                                Component c = bookings_tbl.prepareRenderer(cellRenderer, row, column);
                                int width = c.getPreferredSize().width + bookings_tbl.getIntercellSpacing().width;
                                preferredWidth = Math.max(preferredWidth, width);

                                //  We've exceeded the maximum width, no need to check other rows
                                if (preferredWidth >= maxWidth) {
                                    preferredWidth = maxWidth;
                                    break;
                                }
                            }

                            tableColumn.setPreferredWidth(preferredWidth);
                        }
                    }
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // Filter by Today
    private void filterToday() {
        synchronized (this) {
            try {
                String str = "SELECT * FROM bookings WHERE added_date >= ? ORDER BY added_date DESC";

                stat = con.prepareStatement(str);

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date myDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(myDate);
                calendar.add(Calendar.DAY_OF_MONTH, 0);
                Date newDate = calendar.getTime();
                String the_main_date = dateFormat.format(newDate);

                String t_num = the_main_date;

                stat.setString(1, t_num);

                ResultSet rs = stat.executeQuery();

                dtm_bookings.setRowCount(0);

                while (rs.next()) {
                    String t_no = rs.getString("ticketNumber");
                    String fname = rs.getString("firstName");
                    String lname = rs.getString("lastName");
                    String fullname = fname + " " + lname;
                    String mobile = "      " + rs.getString("phoneNumber") + "      ";
                    String gender = "        " + rs.getString("gender") + "        ";
                    String title = rs.getString("title");
                    String class_ = rs.getString("class");
                    String seat = rs.getString("seat") + " seat(s)";
                    String from = rs.getString("from_station");
                    String depart = "        " + rs.getString("departure_time") + "        ";
                    String to = rs.getString("to_station");
                    String arrive = "        " + rs.getString("arrival_time") + "        ";
                    String t_number = "          " + rs.getString("train_number") + "          ";
                    String t_name = rs.getString("train_name");
                    String date = rs.getString("added_date");

                    bookings_obj = new Object[]{
                        t_no, fullname, mobile, gender, title, class_, seat, from, depart, to, arrive, t_number, t_name, date
                    };

                    dtm_bookings.addRow(bookings_obj);
                    bookings_tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    for (int column = 0; column < bookings_tbl.getColumnCount(); column++) {
                        TableColumn tableColumn = bookings_tbl.getColumnModel().getColumn(column);
                        int preferredWidth = tableColumn.getMinWidth();
                        int maxWidth = tableColumn.getMaxWidth();

                        for (int row = 0; row < bookings_tbl.getRowCount(); row++) {
                            TableCellRenderer cellRenderer = bookings_tbl.getCellRenderer(row, column);
                            Component c = bookings_tbl.prepareRenderer(cellRenderer, row, column);
                            int width = c.getPreferredSize().width + bookings_tbl.getIntercellSpacing().width;
                            preferredWidth = Math.max(preferredWidth, width);

                            //  We've exceeded the maximum width, no need to check other rows
                            if (preferredWidth >= maxWidth) {
                                preferredWidth = maxWidth;
                                break;
                            }
                        }

                        tableColumn.setPreferredWidth(preferredWidth);
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // Filter by a week
    private void filterWeekly() {
        synchronized (this) {
            try {
                String str = "SELECT * FROM bookings WHERE added_date >= ? AND added_date < ? ORDER BY added_date DESC";

                stat = con.prepareStatement(str);

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date myDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(myDate);
                calendar.add(Calendar.DAY_OF_MONTH, -7);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(myDate);
                calendar2.add(Calendar.DAY_OF_MONTH, 0);
                Date newDate = calendar.getTime();
                Date newDate2 = calendar2.getTime();
                String the_main_date = dateFormat.format(newDate);
                String todaysDate = dateFormat.format(newDate2);

                stat.setString(1, the_main_date);
                stat.setString(2, todaysDate);

                ResultSet rs = stat.executeQuery();

                dtm_bookings.setRowCount(0);

                while (rs.next()) {
                    String t_no = rs.getString("ticketNumber");
                    String fname = rs.getString("firstName");
                    String lname = rs.getString("lastName");
                    String fullname = fname + " " + lname;
                    String mobile = "      " + rs.getString("phoneNumber") + "      ";
                    String gender = "        " + rs.getString("gender") + "        ";
                    String title = rs.getString("title");
                    String class_ = rs.getString("class");
                    String seat = rs.getString("seat") + " seat(s)";
                    String from = rs.getString("from_station");
                    String depart = "        " + rs.getString("departure_time") + "        ";
                    String to = rs.getString("to_station");
                    String arrive = "        " + rs.getString("arrival_time") + "        ";
                    String t_number = "          " + rs.getString("train_number") + "          ";
                    String t_name = rs.getString("train_name");
                    String date = rs.getString("added_date");

                    bookings_obj = new Object[]{
                        t_no, fullname, mobile, gender, title, class_, seat, from, depart, to, arrive, t_number, t_name, date
                    };

                    dtm_bookings.addRow(bookings_obj);
                    bookings_tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    for (int column = 0; column < bookings_tbl.getColumnCount(); column++) {
                        TableColumn tableColumn = bookings_tbl.getColumnModel().getColumn(column);
                        int preferredWidth = tableColumn.getMinWidth();
                        int maxWidth = tableColumn.getMaxWidth();

                        for (int row = 0; row < bookings_tbl.getRowCount(); row++) {
                            TableCellRenderer cellRenderer = bookings_tbl.getCellRenderer(row, column);
                            Component c = bookings_tbl.prepareRenderer(cellRenderer, row, column);
                            int width = c.getPreferredSize().width + bookings_tbl.getIntercellSpacing().width;
                            preferredWidth = Math.max(preferredWidth, width);

                            //  We've exceeded the maximum width, no need to check other rows
                            if (preferredWidth >= maxWidth) {
                                preferredWidth = maxWidth;
                                break;
                            }
                        }

                        tableColumn.setPreferredWidth(preferredWidth);
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // Filter by a month
    private void filterMonthly() {
        synchronized (this) {
            try {
                String str = "SELECT * FROM bookings WHERE added_date >= ? AND added_date < ? ORDER BY added_date DESC";

                stat = con.prepareStatement(str);

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date myDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(myDate);
                calendar.add(Calendar.DAY_OF_YEAR, -30);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(myDate);
                calendar2.add(Calendar.DAY_OF_YEAR, 0);
                Date newDate = calendar.getTime();
                Date newDate2 = calendar2.getTime();
                String the_main_date = dateFormat.format(newDate);
                String todaysDate = dateFormat.format(newDate2);

                stat.setString(1, the_main_date);
                stat.setString(2, todaysDate);

                ResultSet rs = stat.executeQuery();

                dtm_bookings.setRowCount(0);

                while (rs.next()) {
                    String t_no = rs.getString("ticketNumber");
                    String fname = rs.getString("firstName");
                    String lname = rs.getString("lastName");
                    String fullname = fname + " " + lname;
                    String mobile = "      " + rs.getString("phoneNumber") + "      ";
                    String gender = "        " + rs.getString("gender") + "        ";
                    String title = rs.getString("title");
                    String class_ = rs.getString("class");
                    String seat = rs.getString("seat") + " seat(s)";
                    String from = rs.getString("from_station");
                    String depart = "        " + rs.getString("departure_time") + "        ";
                    String to = rs.getString("to_station");
                    String arrive = "        " + rs.getString("arrival_time") + "        ";
                    String t_number = "          " + rs.getString("train_number") + "          ";
                    String t_name = rs.getString("train_name");
                    String date = rs.getString("added_date");

                    bookings_obj = new Object[]{
                        t_no, fullname, mobile, gender, title, class_, seat, from, depart, to, arrive, t_number, t_name, date
                    };

                    dtm_bookings.addRow(bookings_obj);
                    bookings_tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    for (int column = 0; column < bookings_tbl.getColumnCount(); column++) {
                        TableColumn tableColumn = bookings_tbl.getColumnModel().getColumn(column);
                        int preferredWidth = tableColumn.getMinWidth();
                        int maxWidth = tableColumn.getMaxWidth();

                        for (int row = 0; row < bookings_tbl.getRowCount(); row++) {
                            TableCellRenderer cellRenderer = bookings_tbl.getCellRenderer(row, column);
                            Component c = bookings_tbl.prepareRenderer(cellRenderer, row, column);
                            int width = c.getPreferredSize().width + bookings_tbl.getIntercellSpacing().width;
                            preferredWidth = Math.max(preferredWidth, width);

                            //  We've exceeded the maximum width, no need to check other rows
                            if (preferredWidth >= maxWidth) {
                                preferredWidth = maxWidth;
                                break;
                            }
                        }

                        tableColumn.setPreferredWidth(preferredWidth);
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // Filter by a year
    private void filterYearly() {
        synchronized (this) {
            try {
                String str = "SELECT * FROM bookings WHERE added_date >= ? AND added_date < ? ORDER BY added_date DESC";

                stat = con.prepareStatement(str);

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date myDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(myDate);
                calendar.add(Calendar.DAY_OF_YEAR, -355);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(myDate);
                calendar2.add(Calendar.DAY_OF_YEAR, 0);
                Date newDate = calendar.getTime();
                Date newDate2 = calendar2.getTime();
                String the_main_date = dateFormat.format(newDate);
                String todaysDate = dateFormat.format(newDate2);

                stat.setString(1, the_main_date);
                stat.setString(2, todaysDate);

                ResultSet rs = stat.executeQuery();

                dtm_bookings.setRowCount(0);

                while (rs.next()) {
                    String t_no = rs.getString("ticketNumber");
                    String fname = rs.getString("firstName");
                    String lname = rs.getString("lastName");
                    String fullname = fname + " " + lname;
                    String mobile = "      " + rs.getString("phoneNumber") + "      ";
                    String gender = "        " + rs.getString("gender") + "        ";
                    String title = rs.getString("title");
                    String class_ = rs.getString("class");
                    String seat = rs.getString("seat") + " seat(s)";
                    String from = rs.getString("from_station");
                    String depart = "        " + rs.getString("departure_time") + "        ";
                    String to = rs.getString("to_station");
                    String arrive = "        " + rs.getString("arrival_time") + "        ";
                    String t_number = "          " + rs.getString("train_number") + "          ";
                    String t_name = rs.getString("train_name");
                    String date = rs.getString("added_date");

                    bookings_obj = new Object[]{
                        t_no, fullname, mobile, gender, title, class_, seat, from, depart, to, arrive, t_number, t_name, date
                    };

                    dtm_bookings.addRow(bookings_obj);
                    bookings_tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    for (int column = 0; column < bookings_tbl.getColumnCount(); column++) {
                        TableColumn tableColumn = bookings_tbl.getColumnModel().getColumn(column);
                        int preferredWidth = tableColumn.getMinWidth();
                        int maxWidth = tableColumn.getMaxWidth();

                        for (int row = 0; row < bookings_tbl.getRowCount(); row++) {
                            TableCellRenderer cellRenderer = bookings_tbl.getCellRenderer(row, column);
                            Component c = bookings_tbl.prepareRenderer(cellRenderer, row, column);
                            int width = c.getPreferredSize().width + bookings_tbl.getIntercellSpacing().width;
                            preferredWidth = Math.max(preferredWidth, width);

                            //  We've exceeded the maximum width, no need to check other rows
                            if (preferredWidth >= maxWidth) {
                                preferredWidth = maxWidth;
                                break;
                            }
                        }

                        tableColumn.setPreferredWidth(preferredWidth);
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // Filter by a long time ago
    private void filterLongTimeAgo() {
        synchronized (this) {
            try {
                String str = "SELECT * FROM bookings WHERE added_date >= ? AND added_date < ? ORDER BY added_date DESC";

                stat = con.prepareStatement(str);

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date myDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(myDate);
                calendar.add(Calendar.DAY_OF_MONTH, -1775);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(myDate);
                calendar2.add(Calendar.DAY_OF_MONTH, 0);
                Date newDate = calendar.getTime();
                Date newDate2 = calendar2.getTime();
                String the_main_date = dateFormat.format(newDate);
                String todaysDate = dateFormat.format(newDate2);

                stat.setString(1, the_main_date);
                stat.setString(2, todaysDate);

                ResultSet rs = stat.executeQuery();

                dtm_bookings.setRowCount(0);

                while (rs.next()) {
                    String t_no = rs.getString("ticketNumber");
                    String fname = rs.getString("firstName");
                    String lname = rs.getString("lastName");
                    String fullname = fname + " " + lname;
                    String mobile = "      " + rs.getString("phoneNumber") + "      ";
                    String gender = "        " + rs.getString("gender") + "        ";
                    String title = rs.getString("title");
                    String class_ = rs.getString("class");
                    String seat = rs.getString("seat") + " seat(s)";
                    String from = rs.getString("from_station");
                    String depart = "        " + rs.getString("departure_time") + "        ";
                    String to = rs.getString("to_station");
                    String arrive = "        " + rs.getString("arrival_time") + "        ";
                    String t_number = "          " + rs.getString("train_number") + "          ";
                    String t_name = rs.getString("train_name");
                    String date = rs.getString("added_date");

                    bookings_obj = new Object[]{
                        t_no, fullname, mobile, gender, title, class_, seat, from, depart, to, arrive, t_number, t_name, date
                    };

                    dtm_bookings.addRow(bookings_obj);
                    bookings_tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    for (int column = 0; column < bookings_tbl.getColumnCount(); column++) {
                        TableColumn tableColumn = bookings_tbl.getColumnModel().getColumn(column);
                        int preferredWidth = tableColumn.getMinWidth();
                        int maxWidth = tableColumn.getMaxWidth();

                        for (int row = 0; row < bookings_tbl.getRowCount(); row++) {
                            TableCellRenderer cellRenderer = bookings_tbl.getCellRenderer(row, column);
                            Component c = bookings_tbl.prepareRenderer(cellRenderer, row, column);
                            int width = c.getPreferredSize().width + bookings_tbl.getIntercellSpacing().width;
                            preferredWidth = Math.max(preferredWidth, width);

                            //  We've exceeded the maximum width, no need to check other rows
                            if (preferredWidth >= maxWidth) {
                                preferredWidth = maxWidth;
                                break;
                            }
                        }

                        tableColumn.setPreferredWidth(preferredWidth);
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // Change filter parameter
    private void filterItemChanged() {
        int itemSelected = filter_jcb.getSelectedIndex();

        switch (itemSelected) {
            case 0:
                filterToday();
                break;
            case 1:
                filterWeekly();
                break;
            case 2:
                filterMonthly();
                break;
            case 3:
                filterYearly();
                break;
            case 4:
                filterLongTimeAgo();
                break;
        }
    }

    // Adding values to Book Tab comboboxes after connection
    private void add_values_to_book_tab_combo_boxes() {
        // ---------------------------------

        synchronized (this) {
            try {
                String str = "SELECT * FROM stations";

                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(str);

                while (rs.next()) {
                    String station_name = rs.getString("station_name");
                    String station_type = rs.getString("station_type");
                    from_station_jcb.addItem(station_name + " (" + station_type + ")");
                }
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
//                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ---------------------------------
        // ---------------------------------
        synchronized (this) {
            try {
                String str = "SELECT * FROM stations";

                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(str);

                while (rs.next()) {
                    String station_name = rs.getString("station_name");
                    String station_type = rs.getString("station_type");
                    to_station_jcb.addItem(station_name + " (" + station_type + ")");
                }
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
//                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ---------------------------------
        // ---------------------------------
        synchronized (this) {
            try {
                String str = "SELECT * FROM trains";

                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(str);

                while (rs.next()) {
                    String train_name = rs.getString("train_name");
                    trains.add(train_name);
                }

                Iterator trains_itr = trains.iterator();
                while (trains_itr.hasNext()) {
                    String t_name = trains_itr.next().toString();
                    train_name_jcb.addItem(t_name);
                }
                trains.clear();
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
//                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Item Listener for The Train Name to Change the values of the Train Number When the item selected changes 
        train_name_jcb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt) {
                synchronized (this) {
                    try {
                        String t_name = train_name_jcb.getSelectedItem().toString();

                        stat = con.prepareStatement("SELECT * FROM trains WHERE train_name = ?");

                        stat.setString(1, t_name);

                        ResultSet rs = stat.executeQuery();

                        train_number_jcb.removeAllItems();

                        while (rs.next()) {
                            String train_number = rs.getString("train_number");
                            train_number_jcb.addItem(train_number);
                        }
                    } catch (SQLException ex) {
//                        JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // ---------------------------------
        // ---------------------------------
        synchronized (this) {
            try {
                String t_name = train_name_jcb.getSelectedItem().toString();

                stat = con.prepareStatement("SELECT * FROM trains WHERE train_name = ?");

                stat.setString(1, t_name);

                ResultSet rs = stat.executeQuery();

                train_number_jcb.removeAllItems();

                while (rs.next()) {
                    String train_number = rs.getString("train_number");
                    train_number_jcb.addItem(train_number);
                }
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
//                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ---------------------------------
    }

    // Adding values to Edit Frame comboboxes after connection
    private void add_values_to_edit_frame_combo_boxes() {
        // ---------------------------------

        synchronized (this) {
            try {
                String str = "SELECT * FROM stations";

                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(str);

                while (rs.next()) {
                    String station_name = rs.getString("station_name");
                    String station_type = rs.getString("station_type");
                    edit_from_station_jcb.addItem(station_name + " (" + station_type + ")");
                }
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
//                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ---------------------------------
        // ---------------------------------
        synchronized (this) {
            try {
                String str = "SELECT * FROM stations";

                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(str);

                while (rs.next()) {
                    String station_name = rs.getString("station_name");
                    String station_type = rs.getString("station_type");
                    edit_to_station_jcb.addItem(station_name + " (" + station_type + ")");
                }
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
//                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ---------------------------------
        // ---------------------------------
        synchronized (this) {
            try {
                String str = "SELECT * FROM trains";

                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(str);

                while (rs.next()) {
                    String train_name = rs.getString("train_name");
                    edit_trains.add(train_name);
                }

                Iterator trains_itr = edit_trains.iterator();
                while (trains_itr.hasNext()) {
                    String t_name = trains_itr.next().toString();
                    edit_train_name_jcb.addItem(t_name);
                }
                edit_trains.clear();
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
//                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Item Listener for The Train Name to Change the values of the Train Number When the item selected changes 
        edit_train_name_jcb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt) {
                synchronized (this) {
                    try {
                        String t_name = edit_train_name_jcb.getSelectedItem().toString();

                        stat = con.prepareStatement("SELECT * FROM trains WHERE train_name = ?");

                        stat.setString(1, t_name);

                        ResultSet rs = stat.executeQuery();

                        edit_train_number_jcb.removeAllItems();

                        while (rs.next()) {
                            String train_number = rs.getString("train_number");
                            edit_train_number_jcb.addItem(train_number);
                        }
                    } catch (SQLException ex) {
//                        JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // ---------------------------------
        // ---------------------------------
        synchronized (this) {
            try {
                String t_name = edit_train_name_jcb.getSelectedItem().toString();

                stat = con.prepareStatement("SELECT * FROM trains WHERE train_name = ?");

                stat.setString(1, t_name);

                ResultSet rs = stat.executeQuery();

                edit_train_number_jcb.removeAllItems();

                while (rs.next()) {
                    String train_number = rs.getString("train_number");
                    edit_train_number_jcb.addItem(train_number);
                }
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(MainFrame, "SQL Error:" + ex.getMessage() + "\nCode : " + ex.getErrorCode(), "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
//                JOptionPane.showMessageDialog(MainFrame, "Error: " + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ---------------------------------
    }

    // Action for each booking clicked in the table for printing
    private void mouseDoubleClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) bookings_tbl.getModel();
            if (bookings_tbl.getRowCount() > 0) {
                if (bookings_tbl.getSelectedRowCount() > 0) {
                    int selectedRow[] = bookings_tbl.getSelectedRows();
                    for (int i : selectedRow) {
                        String ticket_number = bookings_tbl.getValueAt(i, 0).toString();
                        String fullname = bookings_tbl.getValueAt(i, 1).toString();
                        String title = bookings_tbl.getValueAt(i, 4).toString();
                        String class_ = bookings_tbl.getValueAt(i, 5).toString();
                        String from = bookings_tbl.getValueAt(i, 7).toString();
                        String to = bookings_tbl.getValueAt(i, 9).toString();
                        String depart = bookings_tbl.getValueAt(i, 8).toString();
                        String arrive = bookings_tbl.getValueAt(i, 10).toString();
                        String tname = bookings_tbl.getValueAt(i, 12).toString();
                        String tnumber = bookings_tbl.getValueAt(i, 11).toString();
                        String seat = bookings_tbl.getValueAt(i, 6).toString();
                        String date = bookings_tbl.getValueAt(i, 13).toString();

                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                        Date date_ = new Date(date);
                        String newdate = dateFormat.format(date_);
                        String newtime = timeFormat.format(date_);

                        String depart_trim = depart.trim();
                        String arrive_trim = arrive.trim();
                        String tnum_trim = tnumber.trim();

                        String hh_format = "";
                        if (date_.getHours() >= 12) {
                            hh_format = "PM";
                        } else {
                            hh_format = "AM";
                        }

                        int option = JOptionPane.showConfirmDialog(MainFrame, "Want to print ?", "Print Ticket", JOptionPane.YES_NO_OPTION);
                        if (option == 0) {
                            MainFrame.setEnabled(false);
                            Slip sp = new Slip(ticket_number, fullname, title, class_, from, to, depart_trim, arrive_trim, tname, tnum_trim, seat, newdate, newtime, hh_format);
                            if (sp.cancelled) {
                                JOptionPane.showMessageDialog(MainFrame, "Print Cancelled", "Cancel Message", JOptionPane.INFORMATION_MESSAGE);
                                MainFrame.setEnabled(true);
                            } else {
                                JOptionPane.showMessageDialog(MainFrame, "Printed", "Success Message", JOptionPane.INFORMATION_MESSAGE);
                                MainFrame.setEnabled(true);
                            }
                        } else {
                            JOptionPane.showMessageDialog(MainFrame, "Print Cancelled", "Cancel Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    // Action for each booking right clicked in the table for further options
    private void mouseRightClick(MouseEvent e) {
        if (bookings_tbl.getSelectedRowCount() > 0 && e.getButton() == MouseEvent.BUTTON3) {
            right_click_jmu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private void submitButtonActionPerformed(ActionEvent evt) {
        submit();
    }

    private void saveChangesButtonActionPerformed(ActionEvent evt) {
        saveChanges();
    }

    private void saveButtonActionPerformed(ActionEvent evt) {
        save();
    }

    private void clearButtonActionPerformed(ActionEvent evt) {
        clear();
    }

    private void reload_bookingsButtonActionPerformed(ActionEvent evt) {
        reload_bookings();
    }

    private void createStationButtonActionPerformed(ActionEvent evt) {
        create_station();
    }

    private void reloadStationsButtonActionPerformed(ActionEvent evt) {
        reload_stations();
    }

    private void createTrainButtonActionPerformed(ActionEvent evt) {
        create_train();
    }

    private void reloadTrainsButtonActionPerformed(ActionEvent evt) {
        reload_trains();
    }

    private void refreshHomeButtonActionPerformed(ActionEvent evt) {
        refreshHome();
    }

    private void viewButtonActionPerformed(ActionEvent evt) {
        synchronized (this) {
            allViewBookingComponents();
            if (bookings_tbl.getRowCount() > 0) {
                if (bookings_tbl.getSelectedRowCount() > 0) {
                    int selectedRow[] = bookings_tbl.getSelectedRows();
                    for (int i : selectedRow) {
                        String ticket_number = bookings_tbl.getValueAt(i, 0).toString();
                        String fullname = bookings_tbl.getValueAt(i, 1).toString();
                        String mobile = bookings_tbl.getValueAt(i, 2).toString();
                        String gender = bookings_tbl.getValueAt(i, 3).toString();
                        String title = bookings_tbl.getValueAt(i, 4).toString();
                        String class_ = bookings_tbl.getValueAt(i, 5).toString();
                        String from = bookings_tbl.getValueAt(i, 7).toString();
                        String to = bookings_tbl.getValueAt(i, 9).toString();
                        String depart = bookings_tbl.getValueAt(i, 8).toString();
                        String arrive = bookings_tbl.getValueAt(i, 10).toString();
                        String tname = bookings_tbl.getValueAt(i, 12).toString();
                        String tnumber = bookings_tbl.getValueAt(i, 11).toString();
                        String seat = bookings_tbl.getValueAt(i, 6).toString();
                        String date = bookings_tbl.getValueAt(i, 13).toString();

                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                        Date date_ = new Date(date);
                        String newdate = dateFormat.format(date_);
                        String newtime = timeFormat.format(date_);

                        String depart_trim = depart.trim();
                        String arrive_trim = arrive.trim();
                        String tnum_trim = tnumber.trim();
                        String mobile_trim = mobile.trim();
                        String gender_trim = gender.trim();

                        String hh_format = "";
                        if (date_.getHours() >= 12) {
                            hh_format = "PM";
                        } else {
                            hh_format = "AM";
                        }
                        view_name_lbl.setText(fullname);
                        view_date_lbl.setText(newdate);
                        view_t_no_lbl.setText(ticket_number);
                        view_phoneNum_value_lbl.setText(mobile_trim);
                        view_gender_value_lbl.setText(gender_trim);
                        view_title_value_lbl.setText(title);
                        view_class_value_lbl.setText(class_);
                        view_departure_value_lbl.setText(depart_trim);
                        view_from_station_value_lbl.setText(from);
                        view_arrival_value_lbl.setText(arrive_trim);
                        view_to_station_value_lbl.setText(to);
                        view_train_number_value_lbl.setText(tnum_trim);
                        view_train_name_value_lbl.setText(tname);
                        view_no_of_seats_value_lbl.setText(seat);
                    }
                }
            }
        }
    }

    private void editButtonActionPerformed(ActionEvent evt) {
        synchronized (this) {
            allEditBookingComponents();
            add_values_to_edit_frame_combo_boxes();
            if (bookings_tbl.getRowCount() > 0) {
                if (bookings_tbl.getSelectedRowCount() > 0) {
                    int selectedRow[] = bookings_tbl.getSelectedRows();
                    for (int i : selectedRow) {
                        String ticket_number = bookings_tbl.getValueAt(i, 0).toString();

                        try {
                            String str = "SELECT * FROM bookings WHERE ticketNumber = ? ";

                            stat = con.prepareStatement(str);

                            stat.setString(1, ticket_number);

                            ResultSet rs = stat.executeQuery();

                            while (rs.next()) {
                                String t_no = rs.getString("ticketNumber");
                                String fname = rs.getString("firstName");
                                String lname = rs.getString("lastName");
                                String mobile = rs.getString("phoneNumber");
                                String gender = rs.getString("gender");
                                String title = rs.getString("title");
                                String class_ = rs.getString("class");
                                String seat = rs.getString("seat") + " seat(s)";
                                String from = rs.getString("from_station");
                                String depart = rs.getString("departure_time");
                                String to = rs.getString("to_station");
                                String arrive = rs.getString("arrival_time");
                                String t_number = rs.getString("train_number");
                                String t_name = rs.getString("train_name");

                                if (depart.length() == 7) {
                                    edit_departure_hh_txt.setText(depart.substring(0, 1));
                                    edit_departure_mm_txt.setText(depart.substring(2, 5));
                                    edit_departure_am_pm_jcb.setSelectedItem(depart.substring(5, 7));
                                } else {
                                    edit_departure_hh_txt.setText(depart.substring(0, 2));
                                    edit_departure_mm_txt.setText(depart.substring(3, 6));
                                    edit_departure_am_pm_jcb.setSelectedItem(depart.substring(6, 8));
                                }

                                if (arrive.length() == 7) {
                                    edit_arrival_hh_txt.setText(arrive.substring(0, 1));
                                    edit_arrival_mm_txt.setText(arrive.substring(2, 5));
                                    edit_arrival_am_pm_jcb.setSelectedItem(arrive.substring(5, 7));
                                } else {
                                    edit_arrival_hh_txt.setText(arrive.substring(0, 2));
                                    edit_arrival_mm_txt.setText(arrive.substring(3, 6));
                                    edit_arrival_am_pm_jcb.setSelectedItem(arrive.substring(6, 8));
                                }

                                if (seat.length() == 9) {
                                    edit_no_of_seats_txt.setText(seat.substring(0, 1));
                                } else {
                                    edit_no_of_seats_txt.setText(seat.substring(0, 2));
                                }

                                edit_fname_txt.setText(fname);
                                edit_lname_txt.setText(lname);
                                edit_phoneNum_txt.setText(mobile.trim());
                                edit_gender_jcb.setSelectedItem(gender);
                                edit_title_jcb.setSelectedItem(title);
                                edit_class_jcb.setSelectedItem(class_);
                                edit_from_station_jcb.setSelectedItem(from);
                                edit_to_station_jcb.setSelectedItem(to);
                                edit_train_name_jcb.setSelectedItem(t_name);
                                edit_train_number_jcb.setSelectedItem(t_number);
                                global_tNum = t_no;
                            }
                        } catch (SQLException ex) {
                            System.out.println(ex);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }
        }
    }

    private void deleteButtonActionPerformed(ActionEvent evt) {
        int option = JOptionPane.showConfirmDialog(MainFrame, "Do you really want to delete ?", "Question Message", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            synchronized (this) {
                if (bookings_tbl.getRowCount() > 0) {
                    if (bookings_tbl.getSelectedRowCount() > 0) {
                        int selectedRow[] = bookings_tbl.getSelectedRows();
                        for (int i : selectedRow) {
                            String ticket_number = bookings_tbl.getValueAt(i, 0).toString();

                            try {
                                String str = "DELETE FROM bookings WHERE ticketNumber = ? ";

                                stat = con.prepareStatement(str);

                                stat.setString(1, ticket_number);

                                stat.execute();

                                JOptionPane.showMessageDialog(MainFrame, "Deleted ...");
                                reload_bookings();
                                showBookingsCounts();
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(EditFrame, ex, "Error Message", JOptionPane.ERROR_MESSAGE);
//                                System.out.println(ex);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(EditFrame, e, "Error Message", JOptionPane.ERROR_MESSAGE);
//                                System.out.println(e);
                            }
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(EditFrame, "Delete Cancelled", "Cancellation", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void searchButtonActionPerformed(ActionEvent evt) {
        search();
    }

    private void refreshButtonActionPerformed(ActionEvent evt) {
//        MainFrame.dispose();
        synchronized (this) {
            JOptionPane.showMessageDialog(MainFrame, "Retrying connection ...", "Information Message", JOptionPane.INFORMATION_MESSAGE);
            // Setup SQL connection

            connectButtonActionPerformed(evt);

        }
//        MainFrame.setVisible(true);
    }

    private void connectButtonActionPerformed(ActionEvent evt) {
        setConn();
        if (connectedToDB) {
            showTrainsCounts();
            showStationsCounts();
            showBookingsCounts();
            add_values_to_book_tab_combo_boxes();
            showBookings();
            showStations();
            showTrains();
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        new Ticketing();
    }

}
