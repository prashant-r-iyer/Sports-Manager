import javax.swing.*;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;


import java.awt.*;
import java.awt.event.*;

public class GUI {

    private final static String coachUsername = "Coach name";
    private final static String coachPassword = "coachpwd";
    private static TeamMember user;
    private static String username;
    private static String password;
    private final static Font headingFont = new Font("Serif", Font.PLAIN, 30);
    private final static Font subheadingFont = new Font("Serif", Font.ITALIC, 20);
    private static JFrame loginPageFrame;
    private static JFrame manageRatingsFrame;
    private static JPanel manageRatingsPanel;
    private static JComboBox<String> memberInput;
    private static JComboBox<String> dateInput;
    private static String previousDate = "";
    private final static FramesStack frames = new FramesStack();

    public static void openGUI() {

        loginPageFrame = new JFrame("Login page");

        JPanel loginPagePanel = new JPanel();
        loginPagePanel.setLayout(null);

        JLabel introLabel = new JLabel("Welcome to Sports Manager", SwingConstants.CENTER);
        introLabel.setFont(headingFont);
        introLabel.setBounds(250, 100, 500, 50);
        loginPagePanel.add(introLabel);

        JLabel loginLabel = new JLabel("Login Page", SwingConstants.CENTER);
        loginLabel.setFont(subheadingFont);
        loginLabel.setBounds(350, 200, 300, 50);
        loginPagePanel.add(loginLabel);

        JLabel usernameLabel = new JLabel("Username: ", SwingConstants.LEFT);
        usernameLabel.setBounds(350, 300, 100, 20);
        loginPagePanel.add(usernameLabel);

        JTextField usernameInput = new JTextField("Enter username here", SwingConstants.CENTER);
        usernameInput.setBounds(450, 300, 200, 20); 
        loginPagePanel.add(usernameInput);

        JLabel passwordLabel = new JLabel("Password: ", SwingConstants.LEFT);
        passwordLabel.setBounds(350, 400, 100, 20);
        loginPagePanel.add(passwordLabel);

        JPasswordField passwordInput = new JPasswordField("", SwingConstants.CENTER);
        passwordInput.setBounds(450, 400, 200, 20);
        loginPagePanel.add(passwordInput);

        JButton loginSubmitButton = new JButton("Submit");
        loginSubmitButton.setBounds(400, 500, 200, 50);
        loginPagePanel.add(loginSubmitButton);
        loginSubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                password = "";
                username = usernameInput.getText();
                for (char c : passwordInput.getPassword()) {
                    password += String.valueOf(c);
                }

                user = new TeamMember(username, password);

                FileObject memberFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/memberFile.xlsx");

                if (MemberManager.validateLogin(memberFileObject, username, password)) {
                    user = new StudentMember(username, password);
                    memberFileObject.close();
                    loginPageFrame.setVisible(false);
                    frames.push(loginPageFrame);
                    memberHomePage();
                }
                else if (username.equals(coachUsername) && password.equals(coachPassword)) {
                    user = new Coach(username, password);
                    memberFileObject.close();
                    loginPageFrame.setVisible(false);
                    frames.push(loginPageFrame);
                    coachHomePage();
                }
                else {
                    memberFileObject.close();
                    JOptionPane.showMessageDialog(loginPageFrame, "Incorrect username or password.");
                }
            }            
        });

        loginPageFrame.add(loginPagePanel);
        loginPageFrame.setSize(1000, 1000);
        loginPageFrame.setVisible(true);
        loginPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void coachHomePage() {
        JFrame coachHomePageFrame = new JFrame("Home page");

        JPanel coachHomePagePanel = new JPanel();
        coachHomePagePanel.setLayout(null);

        JLabel homeLabel = new JLabel("Home", SwingConstants.CENTER);
        homeLabel.setFont(headingFont);
        homeLabel.setBounds(250, 100, 500, 50);
        coachHomePagePanel.add(homeLabel);

        JLabel welcomeCoachLabel = new JLabel("Welcome coach " + coachUsername, SwingConstants.CENTER);
        welcomeCoachLabel.setFont(subheadingFont);
        welcomeCoachLabel.setBounds(350, 200, 300, 50);
        coachHomePagePanel.add(welcomeCoachLabel);

        JButton manageMembersButton = new JButton("Manage members");
        manageMembersButton.setBounds(400, 300, 200, 50);
        coachHomePagePanel.add(manageMembersButton);
        manageMembersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                coachHomePageFrame.setVisible(false);
                frames.push(coachHomePageFrame);
                manageMembers();
            }
        });

        JButton manageEventsButton = new JButton("Manage events");
        manageEventsButton.setBounds(400, 400, 200, 50);
        coachHomePagePanel.add(manageEventsButton);
        manageEventsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                coachHomePageFrame.setVisible(false);
                frames.push(coachHomePageFrame);
                manageEvents();
            }
        });

        JButton manageRatingsButton = new JButton("Manage ratings");
        manageRatingsButton.setBounds(400, 500, 200, 50);
        coachHomePagePanel.add(manageRatingsButton);
        manageRatingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                coachHomePageFrame.setVisible(false);
                frames.push(coachHomePageFrame);
                manageRatings();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(900, 10, 70, 30);
        coachHomePagePanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                coachHomePageFrame.setVisible(false);
                back();
            }
        });

        coachHomePageFrame.add(coachHomePagePanel);
        coachHomePageFrame.setSize(1000, 1000);
        coachHomePageFrame.setVisible(true);
        coachHomePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void manageMembers() {
        JFrame manageMembersFrame = new JFrame("Manage members");

        JPanel manageMembersPanel = new JPanel();
        manageMembersPanel.setLayout(null);

        JLabel manageMembersLabel = new JLabel("Manage Members", SwingConstants.CENTER);
        manageMembersLabel.setFont(headingFont);
        manageMembersLabel.setBounds(250, 100, 500, 50);
        manageMembersPanel.add(manageMembersLabel);

        JButton addMemberButton = new JButton("Add/Remove member");
        addMemberButton.setBounds(400, 200, 200, 50);
        manageMembersPanel.add(addMemberButton);
        addMemberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manageMembersFrame.setVisible(false);
                frames.push(manageMembersFrame);
                addMember();
            }
        });

        JButton viewMembersButton = new JButton("View members");
        viewMembersButton.setBounds(400, 300, 200, 50);
        manageMembersPanel.add(viewMembersButton);
        viewMembersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manageMembersFrame.setVisible(false);
                frames.push(manageMembersFrame);
                viewMembers();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(900, 10, 70, 30);
        manageMembersPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manageMembersFrame.setVisible(false);
                back();
            }
        });        

        manageMembersFrame.add(manageMembersPanel);
        manageMembersFrame.setSize(1000, 1000);
        manageMembersFrame.setVisible(true);
        manageMembersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void addMember() {
        JFrame addMemberFrame = new JFrame("Add/Remove members");

        JPanel addMemberPanel = new JPanel();
        addMemberPanel.setLayout(null);

        JLabel addMemberLabel = new JLabel("Add/Remove Members", SwingConstants.CENTER);
        addMemberLabel.setFont(headingFont);
        addMemberLabel.setBounds(350, 100, 300, 50);
        addMemberPanel.add(addMemberLabel);

        JLabel nameLabel = new JLabel("Name: ", SwingConstants.LEFT);
        nameLabel.setBounds(350, 200, 100, 20);
        addMemberPanel.add(nameLabel);

        JTextField nameInput = new JTextField("Enter name here", SwingConstants.CENTER);
        nameInput.setBounds(450, 200, 200, 20); 
        addMemberPanel.add(nameInput);

        JLabel ageLabel = new JLabel("Age: ", SwingConstants.LEFT);
        ageLabel.setBounds(350, 300, 100, 20);
        addMemberPanel.add(ageLabel);

        JTextField ageInput = new JTextField("Enter age here", SwingConstants.CENTER);
        ageInput.setBounds(450, 300, 200, 20);
        addMemberPanel.add(ageInput);

        JLabel genderLabel = new JLabel("Gender: ", SwingConstants.LEFT);
        genderLabel.setBounds(350, 400, 100, 20);
        addMemberPanel.add(genderLabel);

        String[] genders = {"Male", "Female", "Other"};
        JComboBox<String> genderInput = new JComboBox<String>(genders);
        genderInput.setBounds(450, 400, 200, 20);
        addMemberPanel.add(genderInput);

        JLabel divisionLabel = new JLabel("Division: ", SwingConstants.LEFT);
        divisionLabel.setBounds(350, 500, 100, 20);
        addMemberPanel.add(divisionLabel);

        String[] divisions = {"1", "2", "3", "4"};
        JComboBox<String> divisionInput = new JComboBox<String>(divisions);
        divisionInput.setBounds(450, 500, 200, 20);
        addMemberPanel.add(divisionInput);

        JLabel passwordLabel = new JLabel("User password: ", SwingConstants.LEFT);
        passwordLabel.setBounds(350, 600, 100, 20);
        addMemberPanel.add(passwordLabel);

        JTextField passwordInput = new JPasswordField("Enter user password here", SwingConstants.CENTER);
        passwordInput.setBounds(450, 600, 200, 20);
        addMemberPanel.add(passwordInput);

        JButton addMemberButton = new JButton("Add");
        addMemberButton.setBounds(300, 700, 100, 20);
        addMemberPanel.add(addMemberButton);
        addMemberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentMember addedMember = new StudentMember(nameInput.getText(), Integer.parseInt(ageInput.getText()), (String) genderInput.getSelectedItem(), Integer.parseInt((String) divisionInput.getSelectedItem()), passwordInput.getText());
                FileObject memberFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/memberFile.xlsx");
                MemberManager.addMember(memberFileObject, addedMember);
                memberFileObject.close();
            }
        });

        JButton removeMemberButton = new JButton("Remove");
        removeMemberButton.setBounds(600, 700, 100, 20);
        addMemberPanel.add(removeMemberButton);
        removeMemberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentMember removedMember = new StudentMember(nameInput.getText(), Integer.parseInt(ageInput.getText()), (String) genderInput.getSelectedItem(), Integer.parseInt((String) divisionInput.getSelectedItem()), passwordInput.getText());
                FileObject memberFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/memberFile.xlsx");
                MemberManager.removeMember(memberFileObject, removedMember);
                memberFileObject.close();
            }
        });

        FileObject memberFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/memberFile.xlsx");
        FileObject eventFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/eventFile.xlsx");
        
        // RatingManager.updateTableLength(memberFileObject.getNumRows(), eventFileObject.getNumRows());
        
        memberFileObject.close();
        eventFileObject.close();

        JButton backButton = new JButton("Back");
        backButton.setBounds(900, 10, 70, 30);
        addMemberPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMemberFrame.setVisible(false);
                back();
            }
        });

        addMemberFrame.add(addMemberPanel);
        addMemberFrame.setSize(1000, 1000);
        addMemberFrame.setVisible(true);
        addMemberFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void viewMembers() {
        JFrame viewMembersFrame = new JFrame("View members");

        JPanel viewMembersPanel = new JPanel();
        viewMembersPanel.setLayout(null);

        JLabel viewMembersLabel = new JLabel("View Members", SwingConstants.CENTER);
        viewMembersLabel.setFont(headingFont);
        viewMembersLabel.setBounds(350, 100, 300, 50);
        viewMembersPanel.add(viewMembersLabel);

        FileObject memberFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/memberFile.xlsx");
        String[][] tableData = memberFileObject.get2DArray(5);
        memberFileObject.close();
        String[] tableHeadings = {"Name", "Age", "Gender", "Division", "Password"};

        JTable memberTable = new JTable(tableData, tableHeadings);
        memberTable.setBounds(100, 200, 800, 500);
        viewMembersPanel.add(memberTable);

        JButton backButton = new JButton("Back");
        backButton.setBounds(900, 10, 70, 30);
        viewMembersPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewMembersFrame.setVisible(false);
                back();
            }
        });

        viewMembersFrame.add(viewMembersPanel);
        viewMembersFrame.setSize(1000, 1000);
        viewMembersFrame.setVisible(true);
        viewMembersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void manageEvents() {
        JFrame manageEventsFrame = new JFrame("Manage events");

        JPanel manageEventsPanel = new JPanel();
        manageEventsPanel.setLayout(null);

        JLabel manageEventsLabel = new JLabel("Manage Events", SwingConstants.CENTER);
        manageEventsLabel.setFont(headingFont);
        manageEventsLabel.setBounds(250, 100, 500, 50);
        manageEventsPanel.add(manageEventsLabel);

        JButton addEventButton = new JButton("Add/Remove event");
        addEventButton.setBounds(400, 200, 200, 50);
        manageEventsPanel.add(addEventButton);
        addEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manageEventsFrame.setVisible(false);
                frames.push(manageEventsFrame);
                addEvent();
            }
        });

        JButton viewEventsButton = new JButton("View events");
        viewEventsButton.setBounds(400, 300, 200, 50);
        manageEventsPanel.add(viewEventsButton);
        viewEventsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manageEventsFrame.setVisible(false);
                frames.push(manageEventsFrame);
                viewEvents();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(900, 10, 70, 30);
        manageEventsPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manageEventsFrame.setVisible(false);
                back();
            }
        });

        manageEventsFrame.add(manageEventsPanel);
        manageEventsFrame.setSize(1000, 1000);
        manageEventsFrame.setVisible(true);
        manageEventsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void addEvent() {
        JFrame addEventFrame = new JFrame("Add/Remove events");

        JPanel addEventPanel = new JPanel();
        addEventPanel.setLayout(null);

        JLabel addMemberLabel = new JLabel("Add/Remove Events", SwingConstants.CENTER);
        addMemberLabel.setFont(headingFont);
        addMemberLabel.setBounds(350, 100, 300, 50);
        addEventPanel.add(addMemberLabel);

        JLabel eventLabel = new JLabel("Event: ", SwingConstants.LEFT);
        eventLabel.setBounds(350, 200, 200, 20);
        addEventPanel.add(eventLabel);

        String[] events = {"Training", "Match"};
        JComboBox<String> eventInput = new JComboBox<String>(events);
        eventInput.setBounds(550, 200, 200, 20);
        addEventPanel.add(eventInput);

        JLabel dateLabel = new JLabel("Date (dd/mm/yy): ", SwingConstants.LEFT);
        dateLabel.setBounds(350, 250, 200, 20);
        addEventPanel.add(dateLabel);

        JTextField dateInput = new JTextField("Enter date here", SwingConstants.CENTER);
        dateInput.setBounds(550, 250, 200, 20);
        addEventPanel.add(dateInput);

        JLabel timeLabel = new JLabel("Time (hh:mm): ", SwingConstants.LEFT);
        timeLabel.setBounds(350, 300, 200, 20);
        addEventPanel.add(timeLabel);

        JTextField timeInput = new JTextField("Enter time here", SwingConstants.CENTER);
        timeInput.setBounds(550, 300, 200, 20);
        addEventPanel.add(timeInput);

        JLabel durationLabel = new JLabel("Duration (minutes): ", SwingConstants.LEFT);
        durationLabel.setBounds(350, 350, 200, 20);
        addEventPanel.add(durationLabel);

        JTextField durationInput = new JTextField("Enter duration here", SwingConstants.CENTER);
        durationInput.setBounds(550, 350, 200, 20);
        addEventPanel.add(durationInput);

        JLabel locationLabel = new JLabel("Location: ", SwingConstants.LEFT);
        locationLabel.setBounds(350, 400, 200, 20);
        addEventPanel.add(locationLabel);

        JTextField locationInput = new JTextField("Enter location here", SwingConstants.CENTER);
        locationInput.setBounds(550, 400, 200, 20);
        addEventPanel.add(locationInput);

        JLabel divisionLabel = new JLabel("Division: ", SwingConstants.LEFT);
        divisionLabel.setBounds(350, 450, 200, 20);
        addEventPanel.add(divisionLabel);

        String[] divisions = {"1", "2", "3", "4"};
        JComboBox<String> divisionInput = new JComboBox<String>(divisions);
        divisionInput.setBounds(550, 450, 200, 20);
        addEventPanel.add(divisionInput);

        JLabel genderLabel = new JLabel("Gender: ", SwingConstants.LEFT);
        genderLabel.setBounds(350, 500, 200, 20);
        addEventPanel.add(genderLabel);

        String[] genders = {"Male", "Female"};
        JComboBox<String> genderInput = new JComboBox<String>(genders);
        genderInput.setBounds(550, 500, 200, 20);
        addEventPanel.add(genderInput);

        JButton addEventButton = new JButton("Add");
        addEventButton.setBounds(300, 600, 100, 20);
        addEventPanel.add(addEventButton);
        addEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Event addedEvent = new Event((String) eventInput.getSelectedItem(), dateInput.getText(), timeInput.getText(), Integer.parseInt(durationInput.getText()), locationInput.getText(), Integer.parseInt((String) divisionInput.getSelectedItem()), (String) genderInput.getSelectedItem());
                FileObject eventFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/eventFile.xlsx");
                eventFileObject.addRow(addedEvent);
                eventFileObject.close();
            }
        });

        JButton removeEventButton = new JButton("Remove");
        removeEventButton.setBounds(600, 600, 100, 20);
        addEventPanel.add(removeEventButton);
        removeEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Event removedEvent = new Event((String) eventInput.getSelectedItem(), dateInput.getText(), timeInput.getText(), Integer.parseInt(durationInput.getText()), locationInput.getText(), Integer.parseInt((String) divisionInput.getSelectedItem()), (String) genderInput.getSelectedItem());
                FileObject eventFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/eventFile.xlsx");
                eventFileObject.removeRow(removedEvent);
                eventFileObject.close();
            }
        });

        FileObject memberFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/memberFile.xlsx");
        FileObject eventFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/eventFile.xlsx");
        
        // RatingManager.updateTableLength(memberFileObject.getNumRows(), eventFileObject.getNumRows());
        
        memberFileObject.close();
        eventFileObject.close();

        JButton backButton = new JButton("Back");
        backButton.setBounds(900, 10, 70, 30);
        addEventPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEventFrame.setVisible(false);
                back();
            }
        });

        addEventFrame.add(addEventPanel);
        addEventFrame.setSize(1000, 1000);
        addEventFrame.setVisible(true);
        addEventFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void viewEvents() {
        JFrame viewEventsFrame = new JFrame("View events");

        JPanel viewEventsPanel = new JPanel();
        viewEventsPanel.setLayout(null);

        JLabel viewMembersLabel = new JLabel("View Events", SwingConstants.CENTER);
        viewMembersLabel.setFont(headingFont);
        viewMembersLabel.setBounds(350, 100, 300, 50);
        viewEventsPanel.add(viewMembersLabel);

        FileObject memberFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/eventFile.xlsx");
        String[][] tableData = memberFileObject.get2DArray(7);
        memberFileObject.close();
        String[] tableHeadings = {"Event", "Date", "Time", "Duration", "Location", "Division", "Gender"};

        JTable memberTable = new JTable(tableData, tableHeadings);
        memberTable.setBounds(100, 200, 800, 500);
        viewEventsPanel.add(memberTable);

        JButton backButton = new JButton("Back");
        backButton.setBounds(900, 10, 70, 30);
        viewEventsPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewEventsFrame.setVisible(false);
                back();
            }
        });

        viewEventsFrame.add(viewEventsPanel);
        viewEventsFrame.setSize(1000, 1000);
        viewEventsFrame.setVisible(true);
        viewEventsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void manageRatings() {
        manageRatingsFrame = new JFrame("Manage ratings");

        manageRatingsPanel = new JPanel();
        manageRatingsPanel.setLayout(null);

        FileObject memberFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/memberFile.xlsx");
        int numMembers = memberFileObject.getNumRows();
        memberFileObject.close();
        FileObject eventFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/eventFile.xlsx");
        int numEvents = eventFileObject.getNumRows();
        eventFileObject.close();

        FileObject ratingFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/ratingFile.xlsx");
        FileObject feedbackFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/feedbackFile.xlsx");

        ArrayList<String> memberNames = new ArrayList<String>();
        for (int row = 1; row <= numMembers; row++) {
            Row currentRow = memberFileObject.getSheet().getRow(row);
            memberNames.add(currentRow.getCell(0).getStringCellValue());
        }

        ArrayList<String> eventDates = new ArrayList<String>();
        for (int row = 1; row <= numEvents; row++) {
            Row currentRow = eventFileObject.getSheet().getRow(row);
            eventDates.add(currentRow.getCell(1).getStringCellValue());
        }

        if (ratingFileObject.getSheet().getRow(0) == null) {
            ratingFileObject.getSheet().createRow(0);
        }
        Row firstRatingRow = ratingFileObject.getSheet().getRow(0);
        for (int column = 1; column <= numEvents; column++) {
            if (firstRatingRow.getCell(column) == null) {
                firstRatingRow.createCell(column);
                firstRatingRow.getCell(column).setCellType(CellType.STRING);
            }
            firstRatingRow.getCell(column).setCellValue(eventDates.get(column - 1));
        }
        for (int row = 1; row <= numMembers; row++) {
            Row currentRow = ratingFileObject.getSheet().getRow(row);
            if (currentRow == null) {
                ratingFileObject.getSheet().createRow(row);
                currentRow = ratingFileObject.getSheet().getRow(row);
            }
            if (currentRow.getCell(0) == null) {
                currentRow.createCell(0);
                currentRow.getCell(0).setCellType(CellType.STRING);
            }
            currentRow.getCell(0).setCellValue(memberNames.get(row - 1));
        }
        for (int row = 1; row <= numMembers; row++) {
            Row currentRow = ratingFileObject.getSheet().getRow(row);
            for (int column = 1; column <= numEvents; column++) {
                if (currentRow.getCell(column) == null) {
                    currentRow.createCell(column);
                    currentRow.getCell(column).setCellType(CellType.STRING);
                    currentRow.getCell(column).setCellValue("-1");
                }
            }
        }

        if (feedbackFileObject.getSheet().getRow(0) == null) {
            feedbackFileObject.getSheet().createRow(0);
        }
        Row firstFeedbackRow = feedbackFileObject.getSheet().getRow(0);
        for (int column = 1; column <= numEvents; column++) {
            if (firstFeedbackRow.getCell(column) == null) {
                firstFeedbackRow.createCell(column);
                firstFeedbackRow.getCell(column).setCellType(CellType.STRING);
            }
            firstFeedbackRow.getCell(column).setCellValue(eventDates.get(column - 1));
        }
        for (int row = 1; row <= numMembers; row++) {
            Row currentRow = feedbackFileObject.getSheet().getRow(row);
            if (currentRow == null) {
                feedbackFileObject.getSheet().createRow(row);
                currentRow = feedbackFileObject.getSheet().getRow(row);
            }
            if (currentRow.getCell(0) == null) {
                currentRow.createCell(0);
                currentRow.getCell(0).setCellType(CellType.STRING);
            }
            currentRow.getCell(0).setCellValue(memberNames.get(row - 1));
        }
        for (int row = 1; row <= numMembers; row++) {
            Row currentRow = feedbackFileObject.getSheet().getRow(row);
            for (int column = 1; column <= numEvents; column++) {
                if (currentRow.getCell(column) == null) {
                    currentRow.createCell(column);
                    currentRow.getCell(column).setCellType(CellType.STRING);
                    currentRow.getCell(column).setCellValue("-1");
                }
            }
        }

        // RatingManager.updateTableLength(numMembers, numEvents);

        JLabel manageRatingsLabel = new JLabel("Manage Ratings", SwingConstants.CENTER);
        manageRatingsLabel.setFont(headingFont);
        manageRatingsLabel.setBounds(350, 100, 300, 50);
        manageRatingsPanel.add(manageRatingsLabel);

        JLabel dateLabel = new JLabel("Date: ", SwingConstants.LEFT);
        dateLabel.setBounds(350, 200, 100, 20);
        manageRatingsPanel.add(dateLabel);

        String[] dateArray = new String[eventDates.size()];
        for (int eventCount = 0; eventCount < eventDates.size(); eventCount++) {
            dateArray[eventCount] = eventDates.get(eventCount);
        }
        dateInput = new JComboBox<String>(dateArray);
        dateInput.setSelectedItem((String) previousDate);
        dateInput.setBounds(450, 200, 200, 20);
        manageRatingsPanel.add(dateInput);
        dateInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previousDate = (String) dateInput.getSelectedItem();
                manageRatingsFrame.setVisible(false);
                manageRatings();
                displayMemberInfo(RatingManager.getNameArray((String) dateInput.getSelectedItem()));
            }
        });

        JLabel ratingLabel = new JLabel("Rating: ", SwingConstants.LEFT);
        ratingLabel.setBounds(350, 400, 100, 20);
        manageRatingsPanel.add(ratingLabel);

        String[] ratingArray = {"1", "2","3", "4", "5"};
        JComboBox<String> ratingInput = new JComboBox<String>(ratingArray);
        ratingInput.setBounds(450, 400, 200, 20); 
        manageRatingsPanel.add(ratingInput);

        JLabel feedbackLabel = new JLabel("Feedback: ", SwingConstants.LEFT);
        feedbackLabel.setBounds(350, 500, 100, 20);
        manageRatingsPanel.add(feedbackLabel);

        JTextField feedbackInput = new JTextField("Enter feedback here", SwingConstants.CENTER);
        feedbackInput.setBounds(450, 500, 200, 20); 
        manageRatingsPanel.add(feedbackInput);

        ratingFileObject.close();
        feedbackFileObject.close();

        JButton manageRatingsSubmitButton = new JButton("Submit");
        manageRatingsSubmitButton.setBounds(400, 600, 200, 50);
        manageRatingsPanel.add(manageRatingsSubmitButton);
        manageRatingsSubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RatingManager.updateRatings((String) memberInput.getSelectedItem(), (String) dateInput.getSelectedItem(), (String) ratingInput.getSelectedItem(), feedbackInput.getText(), numEvents);
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(900, 10, 70, 30);
        manageRatingsPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manageRatingsFrame.setVisible(false);
                back();
            }
        });

        manageRatingsFrame.add(manageRatingsPanel);
        manageRatingsFrame.setSize(1000, 1000);
        manageRatingsFrame.setVisible(true);
        manageRatingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void memberHomePage() {
        JFrame memberHomePageFrame = new JFrame("Home page");

        JPanel memberHomePagePanel = new JPanel();
        memberHomePagePanel.setLayout(null);

        JLabel homeLabel = new JLabel("Home", SwingConstants.CENTER);
        homeLabel.setFont(headingFont);
        homeLabel.setBounds(250, 100, 500, 50);
        memberHomePagePanel.add(homeLabel);

        JLabel welcomeMemberLabel = new JLabel("Welcome " + username, SwingConstants.CENTER);
        welcomeMemberLabel.setFont(subheadingFont);
        welcomeMemberLabel.setBounds(350, 200, 300, 50);
        memberHomePagePanel.add(welcomeMemberLabel);

        JButton viewEvents = new JButton("View events");
        viewEvents.setBounds(400, 300, 200, 50);
        memberHomePagePanel.add(viewEvents);
        viewEvents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                memberHomePageFrame.setVisible(false);
                frames.push(memberHomePageFrame);
                viewEvents2();
            }
        });

        JButton viewPerformanceButton = new JButton("View performance");
        viewPerformanceButton.setBounds(400, 400, 200, 50);
        memberHomePagePanel.add(viewPerformanceButton);
        viewPerformanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                memberHomePageFrame.setVisible(false);
                frames.push(memberHomePageFrame);
                viewPerformance();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(900, 10, 70, 30);
        memberHomePagePanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                memberHomePageFrame.setVisible(false);
                back();
            }
        });

        memberHomePageFrame.add(memberHomePagePanel);
        memberHomePageFrame.setSize(1000, 1000);
        memberHomePageFrame.setVisible(true);
        memberHomePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void viewEvents2() {
        JFrame viewEventsFrame = new JFrame("View events");

        JPanel viewEventsPanel = new JPanel();
        viewEventsPanel.setLayout(null);

        JLabel viewMembersLabel = new JLabel("View Events", SwingConstants.CENTER);
        viewMembersLabel.setFont(headingFont);
        viewMembersLabel.setBounds(350, 100, 300, 50);
        viewEventsPanel.add(viewMembersLabel);

        FileObject memberFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/memberFile.xlsx");
        ArrayList<String> currentStudentDetails = memberFileObject.findDetails(username, password);
        memberFileObject.close();
        StudentMember currentStudent = new StudentMember(username, Integer.parseInt(currentStudentDetails.get(1)), currentStudentDetails.get(2), Integer.parseInt(currentStudentDetails.get(3)), password);

        FileObject eventFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/eventFile.xlsx");
        String[][] allTableData = eventFileObject.get2DArray(7);
        int rowCount = 0;
        for (int row = 0; row < allTableData.length; row++) {
            Row currentRow = eventFileObject.getSheet().getRow(row);
            if (currentStudent.getGender().equals(currentRow.getCell(6).getStringCellValue()) && Integer.toString(currentStudent.getDivision()).equals(currentRow.getCell(5).getStringCellValue())) {
                rowCount++;
            }
        }

        String[][] tableData = new String[rowCount + 1][7];
        tableData[0][0] = "EVENT";
        tableData[0][1] = "DATE";
        tableData[0][2] = "TIME";
        tableData[0][3] = "DURATION";
        tableData[0][4] = "LOCATION";
        tableData[0][5] = "DIVISION";
        tableData[0][6] = "GENDER";
        int row = 0;
        int count = 1;
        while (row < rowCount) {
            Row currentRow = eventFileObject.getSheet().getRow(count);
            if (currentStudent.getGender().equals(currentRow.getCell(6).getStringCellValue()) && Integer.toString(currentStudent.getDivision()).equals(currentRow.getCell(5).getStringCellValue())) {
                tableData[row + 1][0] = currentRow.getCell(0).getStringCellValue();
                tableData[row + 1][1] = currentRow.getCell(1).getStringCellValue();
                tableData[row + 1][2] = currentRow.getCell(2).getStringCellValue();
                tableData[row + 1][3] = currentRow.getCell(3).getStringCellValue();
                tableData[row + 1][4] = currentRow.getCell(4).getStringCellValue();
                tableData[row + 1][5] = currentRow.getCell(5).getStringCellValue();
                tableData[row + 1][6] = currentRow.getCell(6).getStringCellValue();
                row++;
            }
            count++;
        }

        eventFileObject.close();

        String[] tableHeadings = {"Event", "Date", "Time", "Duration", "Location", "Division", "Gender"};

        JTable memberTable = new JTable(tableData, tableHeadings);
        memberTable.setBounds(100, 200, 800, 500);
        viewEventsPanel.add(memberTable);

        JButton backButton = new JButton("Back");
        backButton.setBounds(900, 10, 70, 30);
        viewEventsPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewEventsFrame.setVisible(false);
                back();
            }
        });

        viewEventsFrame.add(viewEventsPanel);
        viewEventsFrame.setSize(1000, 1000);
        viewEventsFrame.setVisible(true);
        viewEventsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void viewPerformance() {
        JFrame viewPerformanceFrame = new JFrame("View performance");

        JPanel viewPerformancePanel = new JPanel();
        viewPerformancePanel.setLayout(null);

        JLabel viewPerformanceLabel = new JLabel("View Events", SwingConstants.CENTER);
        viewPerformanceLabel.setFont(headingFont);
        viewPerformanceLabel.setBounds(350, 100, 300, 50);
        viewPerformancePanel.add(viewPerformanceLabel);

        ArrayList<String> tableDates = new ArrayList<String>();
        ArrayList<String> tableRatings = new ArrayList<String>();
        ArrayList<String> tableFeedback = new ArrayList<String>();

        FileObject memberFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/memberFile.xlsx");
        FileObject eventFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/EventFile.xlsx");
        FileObject ratingFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/ratingFile.xlsx");
        FileObject feedbackFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/feedbackFile.xlsx");
        ArrayList<String> currentStudentDetails = memberFileObject.findDetails(username, password);
        StudentMember currentStudent = new StudentMember(username, Integer.parseInt(currentStudentDetails.get(1)), currentStudentDetails.get(2), Integer.parseInt(currentStudentDetails.get(3)), password);

        for (int eventRow = 1; eventRow <= eventFileObject.getNumRows(); eventRow++) {
            Row currentRow = eventFileObject.getSheet().getRow(eventRow);
            if (currentStudent.getGender().equals(currentRow.getCell(6).getStringCellValue()) && Integer.toString(currentStudent.getDivision()).equals(currentRow.getCell(5).getStringCellValue())) {
                tableDates.add(currentRow.getCell(1).getStringCellValue());
                for (int ratingRow = 1; ratingRow <= ratingFileObject.getNumRows(); ratingRow++) {
                    if (ratingFileObject.getSheet().getRow(ratingRow).getCell(0).getStringCellValue().equals(username)) {
                        tableRatings.add(ratingFileObject.getSheet().getRow(ratingRow).getCell(eventRow).getStringCellValue());
                        tableFeedback.add(feedbackFileObject.getSheet().getRow(ratingRow).getCell(eventRow).getStringCellValue());
                    }
                }
            }
        }

        memberFileObject.close();
        eventFileObject.close();
        ratingFileObject.close();
        feedbackFileObject.close();

        String[][] tableData = new String[tableDates.size() + 1][3];
        tableData[0][0] = "DATE";
        tableData[0][1] = "RATING";
        tableData[0][2] = "FEEDBACK";
        String[] tableHeadings = {"Date", "Rating", "Feedback"};
        for (int tableRowCount = 0; tableRowCount < tableDates.size(); tableRowCount++) {
            tableData[tableRowCount + 1][0] = tableDates.get(tableRowCount);
            tableData[tableRowCount + 1][1] = tableRatings.get(tableRowCount);
            tableData[tableRowCount + 1][2] = tableFeedback.get(tableRowCount);
        }

        JTable memberTable = new JTable(tableData, tableHeadings);
        memberTable.setBounds(100, 200, 800, 500);
        viewPerformancePanel.add(memberTable);

        JButton backButton = new JButton("Back");
        backButton.setBounds(900, 10, 70, 30);
        viewPerformancePanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPerformanceFrame.setVisible(false);
                back();
            }
        });

        viewPerformanceFrame.add(viewPerformancePanel);
        viewPerformanceFrame.setSize(1000, 1000);
        viewPerformanceFrame.setVisible(true);
        viewPerformanceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void displayMemberInfo(String[] memberArray){
        JLabel memberLabel = new JLabel("Member: ", SwingConstants.LEFT);
        memberLabel.setBounds(350, 300, 100, 20);
        manageRatingsPanel.add(memberLabel);

        memberInput = new JComboBox<String>(memberArray);
        memberInput.setBounds(450, 300, 200, 20); 
        manageRatingsPanel.add(memberInput);
    }

    public static void back() {
        frames.pop().setVisible(true);
    }

    public static void logout() {
        while (!frames.isEmpty()) {
            frames.pop();
        }
        frames.push(loginPageFrame);
        loginPageFrame.setVisible(true);
    }

    // JButton backButton = new JButton("Back");
    // backButton.setBounds(900, 10, 70, 30);
    // PANEL.add(backButton);
    // backButton.addActionListener(new ActionListener() {
    //     public void actionPerformed(ActionEvent e) {
    //         FRAME.setVisible(false);
    //         back();
    //     }
    // });

    // JButton logoutButton = new JButton("Logout");
    // logoutButton.setBounds(800, 10, 70, 30);
    // viewEventsPanel.add(logoutButton);
    // logoutButton.addActionListener(new ActionListener() {
    //     public void actionPerformed(ActionEvent e) {
    //         viewEventsFrame.setVisible(false);
    //         logout();
    //     }
    // });

    /*
    Parallel arrays: array of students, parallel array with participation
    */

}
