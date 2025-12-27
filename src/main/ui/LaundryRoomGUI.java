package ui;

import model.LaundryRoom;
import model.LaundryMachine;
import model.Status;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

@ExcludeFromJacocoGeneratedReport
public class LaundryRoomGUI extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final String JSON_STORE = "projectStarter/src/data/testReaderGeneralLaundryRoom.json";

    private LaundryRoom laundryRoom;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private DefaultListModel<LaundryMachine> machineListModel;
    private JList<LaundryMachine> machineJList;

    public LaundryRoomGUI() {
        super("Qelexen Laundry Manager");
        initializeFields();
        initializeGraphics();
    }

    private void initializeFields() {
        laundryRoom = new LaundryRoom();
        laundryRoom.initializeMachines();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    private void initializeGraphics() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                printEventLog();
            }
        });

        setLayout(new BorderLayout());

        createMachineListPanel();
        refreshList();
        createButtonPanel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void printEventLog() {
        for (model.Event e : model.EventLog.getInstance()) {
            System.out.println(e.toString());
        }
    }

    // private void createMachineListPanel() {
    // machineListModel = new DefaultListModel<>();
    // machineJList = new JList<>(machineListModel);

    // machineJList.setCellRenderer(new DefaultListCellRenderer() {

    // private final ImageIcon icon = new ImageIcon(
    // new ImageIcon("projectStarter/src/data/Machine.png")
    // .getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));

    // @Override
    // public Component getListCellRendererComponent(
    // JList<?> list, Object value, int index,
    // boolean isSelected, boolean cellHasFocus) {

    // JLabel label = (JLabel) super.getListCellRendererComponent(
    // list, value, index, isSelected, cellHasFocus);

    // label.setIcon(icon);
    // label.setHorizontalTextPosition(SwingConstants.RIGHT);

    // if (value instanceof LaundryMachine) {
    // LaundryMachine m = (LaundryMachine) value;
    // label.setText("Machine " + m.getId()
    // + " | Status: " + m.getStatus()
    // + " | Reserved: " + m.getReserved());
    // }

    // return label;
    // }
    // });

    // JScrollPane scroll = new JScrollPane(machineJList);
    // add(scroll, BorderLayout.CENTER);
    // }
    private void createMachineListPanel() {
        machineListModel = new DefaultListModel<>();
        machineJList = new JList<>(machineListModel);

        machineJList.setCellRenderer(createMachineRenderer());

        JScrollPane scroll = new JScrollPane(machineJList);
        add(scroll, BorderLayout.CENTER);
    }

    private ListCellRenderer<Object> createMachineRenderer() {
        return new DefaultListCellRenderer() {

            private final ImageIcon icon = loadMachineIcon();

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);

                configureLabel(label, value, icon);
                return label;
            }
        };
    }

    private ImageIcon loadMachineIcon() {
        Image img = new ImageIcon("projectStarter/src/data/Machine.png")
                .getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private void configureLabel(JLabel label, Object value, ImageIcon icon) {
        label.setIcon(icon);
        label.setHorizontalTextPosition(SwingConstants.RIGHT);

        if (value instanceof LaundryMachine) {
            LaundryMachine m = (LaundryMachine) value;
            label.setText("Machine " + m.getId()
                    + " | Status: " + m.getStatus()
                    + " | Reserved: " + m.getReserved());
        }
    }

    private void refreshList() {
        machineListModel.clear();
        for (LaundryMachine lm : laundryRoom.getlaundryMachines()) {
            machineListModel.addElement(lm);
        }
    }

    // private void createButtonPanel() {
    // JPanel p = new JPanel(new GridLayout(2, 4));

    // addButton(p, "Add Machine", new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // addMachine();
    // }
    // });

    // addButton(p, "Remove Machine", new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // removeMachine();
    // }
    // });

    // addButton(p, "Set Status", new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // setStatus();
    // }
    // });

    // addButton(p, "Reserve", new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // reserve();
    // }
    // });

    // addButton(p, "Unreserve", new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // unreserve();
    // }
    // });

    // addButton(p, "Start Cycle", new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // startCycle();
    // }
    // });

    // addButton(p, "End Cycle", new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // endCycle();
    // }
    // });

    // addButton(p, "Save", new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // save();
    // }
    // });

    // addButton(p, "Load", new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // load();
    // }
    // });

    // add(p, BorderLayout.SOUTH);
    // }

    private void createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(2, 4));
        addAllButtons(p);
        add(p, BorderLayout.SOUTH);
    }

    private void addAllButtons(JPanel p) {
        createButton(p, "Add Machine", "add");
        createButton(p, "Remove Machine", "remove");
        createButton(p, "Set Status", "status");
        createButton(p, "Reserve", "reserve");
        createButton(p, "Unreserve", "unreserve");
        createButton(p, "Start Cycle", "start");
        createButton(p, "End Cycle", "end");
        createButton(p, "Save", "save");
        createButton(p, "Load", "load");
    }

    private void createButton(JPanel p, String text, String command) {
        JButton b = new JButton(text);
        b.setActionCommand(command);
        b.addActionListener(new ButtonHandler());
        p.add(b);
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String c = e.getActionCommand();
            if (c.equals("add")) {
                addMachine();
            } else if (c.equals("remove")) {
                removeMachine();
            } else if (c.equals("status")) {
                setStatus();
            } else if (c.equals("reserve")) {
                reserve();
            } else if (c.equals("unreserve")) {
                unreserve();
            } else if (c.equals("start")) {
                startCycle();
            } else if (c.equals("end")) {
                endCycle();
            } else if (c.equals("save")) {
                save();
            } else if (c.equals("load")) {
                load();
            }
        }
    }

    private String prompt(String msg) {
        return JOptionPane.showInputDialog(this, msg);
    }

    private LaundryMachine selected() {
        LaundryMachine m = machineJList.getSelectedValue();
        if (m == null) {
            JOptionPane.showMessageDialog(this, "No machine selected.");
        }
        return m;
    }

    private void addMachine() {
        String s = prompt("Enter new machine ID:");
        if (s == null) {
            return;
        }

        try {
            int id = Integer.parseInt(s);
            laundryRoom.addMachine(id);
            refreshList();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    private void removeMachine() {
        LaundryMachine m = selected();
        if (m == null) {
            return;
        }

        if (m.getStatus() != Status.OUT_OF_ORDER) {
            JOptionPane.showMessageDialog(this, "Machine must be OUT_OF_ORDER.");
            return;
        }

        laundryRoom.removeBrokenMachine(m.getId());
        refreshList();
    }

    private void setStatus() {
        LaundryMachine m = selected();
        if (m == null) {
            return;
        }

        Status newStatus = (Status) JOptionPane.showInputDialog(
                this,
                "Choose new status:",
                "Set Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Status.values(),
                m.getStatus());

        if (newStatus != null) {
            m.setStatus(newStatus);
            refreshList();
        }
    }

    private void reserve() {
        LaundryMachine m = selected();
        if (m == null) {
            return;
        }

        if (m.getStatus() != Status.AVAILABLE) {
            JOptionPane.showMessageDialog(this, "Machine not available.");
            return;
        }

        String user = prompt("Enter your student ID:");
        if (user != null && !user.trim().isEmpty()) {
            m.setReserved(user.trim());
            refreshList();
        }
    }

    private void unreserve() {
        LaundryMachine m = selected();
        if (m == null) {
            return;
        }

        if (m.getStatus() != Status.RESERVED) {
            JOptionPane.showMessageDialog(this, "Machine is not reserved.");
            return;
        }

        m.cancelReservation();
        refreshList();
    }

    private void startCycle() {
        LaundryMachine m = selected();
        if (m == null) {
            return;
        }

        if (!m.startCycle()) {
            JOptionPane.showMessageDialog(this, "Machine must be RESERVED.");
        }
        refreshList();
    }

    private void endCycle() {
        LaundryMachine m = selected();
        if (m == null) {
            return;
        }

        if (!m.endCycle()) {
            JOptionPane.showMessageDialog(this, "Machine is not RUNNING.");
        }
        refreshList();
    }

    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(laundryRoom);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Save failed.");
        }
    }

    private void load() {
        try {
            laundryRoom = jsonReader.read();
            refreshList();
            JOptionPane.showMessageDialog(this, "Loaded.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Load failed.");
        }
    }

}
