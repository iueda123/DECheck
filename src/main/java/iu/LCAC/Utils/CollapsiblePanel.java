// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// https://ateraimemo.com/Swing/LayoutAnimation.html

package iu.LCAC.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

public final class CollapsiblePanel extends JPanel {

    public CollapsiblePanel(JComponent base_comp, JComponent north_comp, int width, int height) {
        super(new BorderLayout());
        JButton button = new JButton("Find Next(test)");
        button.setFocusable(false);
        JTextField field = new JTextField("", 10);

        Timer animator = new Timer(5, null);
        //ControlsBorderLayout_for_North layout = new ControlsBorderLayout_for_North(animator);
        ControlsBorderLayout_for_West layout = new ControlsBorderLayout_for_West(animator);
        JPanel controls = new JPanel(layout);
        controls.add(north_comp);
        //controls.setName("aaa");
        //controls.setBorder(BorderFactory.createTitledBorder("Search down"));
        //controls.add(new JLabel("Find what:"), BorderLayout.WEST);
        //controls.add(field);
        //controls.add(button, BorderLayout.EAST);
        animator.addActionListener(e -> controls.revalidate());

        Action act = new AbstractAction("Show/Hide Search Box") {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (!animator.isRunning()) {
                    //layout.isHidden = controls.getHeight() == 0;
                    layout.isHidden = controls.getWidth() == 0;
                    animator.start();
                }
            }
        };

        JButton showHideEastButton = new JButton();
        showHideEastButton.setAction(act);
        showHideEastButton.setFocusable(false);
        JPanel basePanel = new JPanel(new BorderLayout());

        //int modifiers = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
        // Java 10: int modifiers = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        //InputMap im = p.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        //im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, modifiers), "open-searchbox");
        //p.getActionMap().put("open-searchbox", act);
        //p.add(controls, BorderLayout.WEST);
        basePanel.add(controls, BorderLayout.EAST);

        JTree tree = new JTree();
        //p.add(new JScrollPane(tree));
        basePanel.add(new JScrollPane(base_comp));
        basePanel.add(showHideEastButton, BorderLayout.CENTER);

        add(basePanel);
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


        frame.getContentPane().add(new CollapsiblePanel( pane_base, pane_upper, 320, 240));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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