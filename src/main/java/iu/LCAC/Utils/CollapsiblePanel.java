// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// https://ateraimemo.com/Swing/LayoutAnimation.html

package iu.LCAC.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public final class CollapsiblePanel extends JPanel {

    JLabel showHideNorthButton = new JLabel("^");
    JLabel showHideEastButton = new JLabel(" < ");
    JLabel showHideSouthButton = new JLabel("v");
    JLabel showHideWestButton = new JLabel(" > ");

    public CollapsiblePanel(JComponent base_comp, JComponent north_comp, int width, int height) {
        super(new BorderLayout());
        JButton button = new JButton("Find Next(test)");
        button.setFocusable(false);
        JTextField field = new JTextField("", 10);
        JPanel outerBasePanel = new JPanel(new BorderLayout());

        Timer animator_for_north = new Timer(5, null);
        ControlsBorderLayout_for_North north_layout = new ControlsBorderLayout_for_North(animator_for_north);
        JPanel northBasePane = new JPanel(north_layout);
        northBasePane.add(north_comp);
        animator_for_north.addActionListener(e -> northBasePane.revalidate());
        showHideEastButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!animator_for_north.isRunning()) {
                    north_layout.isHidden = northBasePane.getWidth() == 0;
                    animator_for_north.start();
                }
            }
        });
        showHideNorthButton.setFocusable(false);
        outerBasePanel.add(northBasePane, BorderLayout.NORTH);


        Timer animator_for_south = new Timer(5, null);
        ControlsBorderLayout_for_North south_layout = new ControlsBorderLayout_for_North(animator_for_south);
        JPanel southBasePane = new JPanel(north_layout);
        northBasePane.add(north_comp);
        animator_for_south.addActionListener(e -> southBasePane.revalidate());
        showHideEastButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!animator_for_south.isRunning()) {
                    south_layout.isHidden = southBasePane.getWidth() == 0;
                    animator_for_south.start();
                }
            }
        });
        showHideSouthButton.setFocusable(false);
        //outerBasePanel.add(southBasePane, BorderLayout.EAST);


        Timer animator_for_east = new Timer(5, null);
        ControlsBorderLayout_for_East east_layout = new ControlsBorderLayout_for_East(animator_for_east);
        JPanel eastBasePane = new JPanel(east_layout);
        eastBasePane.add(new JLabel("EAST"));
        animator_for_east.addActionListener(e -> eastBasePane.revalidate());
        showHideEastButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!animator_for_east.isRunning()) {
                    east_layout.isHidden = eastBasePane.getWidth() == 0;
                    animator_for_east.start();
                }
            }
        });
        showHideEastButton.setFocusable(false);
        outerBasePanel.add(eastBasePane, BorderLayout.EAST);

        Timer animator_for_west = new Timer(5, null);
        ControlsBorderLayout_for_East west_layout = new ControlsBorderLayout_for_East(animator_for_west);
        JPanel westBasePane = new JPanel(west_layout);
        westBasePane.add(new JLabel("WEST"));
        animator_for_west.addActionListener(e -> westBasePane.revalidate());
        showHideWestButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!animator_for_west.isRunning()) {
                    west_layout.isHidden = westBasePane.getWidth() == 0;
                    animator_for_west.start();
                }
                System.out.println("Here");
            }
        });
        showHideWestButton.setFocusable(false);
        //outerBasePanel.add(westBasePane, BorderLayout.WEST);



        //int modifiers = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
        // Java 10: int modifiers = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        //InputMap im = p.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        //im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, modifiers), "open-searchbox");
        //p.getActionMap().put("open-searchbox", act);
        //p.add(controls, BorderLayout.WEST);

        JTree tree = new JTree();
        //p.add(new JScrollPane(tree));
        outerBasePanel.add(new JScrollPane(base_comp));
        outerBasePanel.add(showHideNorthButton, BorderLayout.SOUTH);
        outerBasePanel.add(showHideEastButton, BorderLayout.WEST);

        outerBasePanel.add(new InnerBasePane(new JLabel("中央")));

        add(outerBasePanel);
        //setPreferredSize(new Dimension(width, height));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(CollapsiblePanel::createAndShowGui);
    }

    private static void createAndShowGui() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException ignored) {
            Toolkit.getDefaultToolkit().beep();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getGlobal().severe(ex::getMessage);
            return;
        }
        JFrame frame = new JFrame("LayoutAnimation");
        // frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel pane_upper = new JPanel();
        pane_upper.add(new JLabel("upper"));


        JPanel pane_base = new JPanel();
        pane_base.setLayout(new BoxLayout(pane_base, BoxLayout.Y_AXIS));
        pane_base.add(new JLabel("over"));
        pane_base.add(new JLabel("over"));
        pane_base.add(new JLabel("over"));
        pane_base.add(new JLabel("over"));
        pane_base.add(new JLabel("over"));
        frame.getContentPane().add(new CollapsiblePanel(pane_base, pane_upper, 320, 240));


        //frame.getContentPane().add(new CollapsiblePanel(basePane, pane_upper, 320, 240));


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    class InnerBasePane extends JPanel {
        public InnerBasePane(JComponent comp) {
            this.setLayout(new BorderLayout());

            Box north_box = Box.createHorizontalBox();
            north_box.add(Box.createHorizontalGlue());
            north_box.add(CollapsiblePanel.this.showHideNorthButton);
            north_box.add(Box.createHorizontalGlue());
            add(north_box, BorderLayout.NORTH);

            Box south_box = Box.createHorizontalBox();
            south_box.add(Box.createHorizontalGlue());
            south_box.add(CollapsiblePanel.this.showHideSouthButton);
            south_box.add(Box.createHorizontalGlue());
            add(south_box, BorderLayout.SOUTH);


            Box east_box = Box.createVerticalBox();
            east_box.add(Box.createVerticalGlue());
            east_box.add(CollapsiblePanel.this.showHideEastButton);
            east_box.add(Box.createVerticalGlue());
            add(east_box, BorderLayout.EAST);

            Box west_box = Box.createVerticalBox();
            west_box.add(Box.createVerticalGlue());
            west_box.add(CollapsiblePanel.this.showHideWestButton);
            west_box.add(Box.createVerticalGlue());
            add(west_box, BorderLayout.WEST);


            add(comp, BorderLayout.CENTER);
        }
    }


    class ControlsBorderLayout_for_North extends BorderLayout {
        public boolean isHidden = true;
        private final Timer animator;
        private int controlsHeight;

        public ControlsBorderLayout_for_North(Timer animator) {
            super(5, 5);
            this.animator = animator;
        }

        @Override
        public Dimension preferredLayoutSize(Container target) {
            // synchronized (target.getTreeLock()) {
            Dimension ps = super.preferredLayoutSize(target);
            int defaultHeight = animator.isRunning() ? ps.height : 0;
            if (isHidden) {
                if (target.getHeight() < defaultHeight) {
                    controlsHeight += 5;
                }
            } else {
                if (target.getHeight() > 0) {
                    controlsHeight -= 5;
                }
            }
            if (controlsHeight <= 0) {
                controlsHeight = 0;
                animator.stop();
            } else if (controlsHeight >= defaultHeight) {
                controlsHeight = defaultHeight;
                animator.stop();
            }
            ps.height = controlsHeight;
            return ps;
        }
    }


    class ControlsBorderLayout_for_East extends BorderLayout {
        public boolean isHidden = true;
        private final Timer animator;
        private int controlsWidth;

        public ControlsBorderLayout_for_East(Timer animator) {
            super(5, 5);
            this.animator = animator;
        }

        @Override
        public Dimension preferredLayoutSize(Container target) {
            // synchronized (target.getTreeLock()) {
            Dimension ps = super.preferredLayoutSize(target);
            int defaultWidth = animator.isRunning() ? ps.width : 0;
            if (isHidden) {
                if (target.getWidth() < defaultWidth) {
                    controlsWidth += 5;
                }
            } else {
                if (target.getWidth() > 0) {
                    controlsWidth -= 5;
                }
            }
            if (controlsWidth <= 0) {
                controlsWidth = 0;
                animator.stop();
            } else if (controlsWidth >= defaultWidth) {
                controlsWidth = defaultWidth;
                animator.stop();
            }
            ps.width = controlsWidth;
            return ps;
        }
    }

    class ControlsBorderLayout_for_West extends BorderLayout {
        public boolean isHidden = true;
        private final Timer animator;
        private int controlsWidth;

        public ControlsBorderLayout_for_West(Timer animator) {
            super(5, 5);
            this.animator = animator;
        }

        @Override
        public Dimension preferredLayoutSize(Container target) {
            // synchronized (target.getTreeLock()) {
            Dimension ps = super.preferredLayoutSize(target);
            int defaultWidth = animator.isRunning() ? ps.width : 0;
            if (isHidden) {
                if (target.getWidth() < defaultWidth) {
                    controlsWidth += 5;
                }
            } else {
                if (target.getWidth() > 0) {
                    controlsWidth -= 5;
                }
            }
            if (controlsWidth <= 0) {
                controlsWidth = 0;
                animator.stop();
            } else if (controlsWidth >= defaultWidth) {
                controlsWidth = defaultWidth;
                animator.stop();
            }
            ps.width = controlsWidth;
            return ps;
        }
    }
}