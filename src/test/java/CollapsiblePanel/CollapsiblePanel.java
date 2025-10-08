// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// https://ateraimemo.com/Swing/LayoutAnimation.html

package CollapsiblePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public final class CollapsiblePanel extends JPanel {

    private CollapsiblePanel(JComponent base_comp, JComponent overlay_comp) {
        super(new BorderLayout());
        JButton button = new JButton("Find Next(test)");
        button.setFocusable(false);
        JTextField field = new JTextField("", 10);

        Timer animator = new Timer(5, null);
        ControlsBorderLayout layout = new ControlsBorderLayout(animator);
        JPanel controls = new JPanel(layout);
        controls.add(overlay_comp);
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
                    layout.isHidden = controls.getHeight() == 0;
                    animator.start();
                }
            }
        };

        JButton showHideButton = new JButton();
        showHideButton.setAction(act);
        showHideButton.setFocusable(false);
        JPanel p = new JPanel(new BorderLayout());

        int modifiers = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
        // Java 10: int modifiers = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        InputMap im = p.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, modifiers), "open-searchbox");
        p.getActionMap().put("open-searchbox", act);
        p.add(controls, BorderLayout.NORTH);

        JTree tree = new JTree();
        //p.add(new JScrollPane(tree));
        p.add(new JScrollPane(base_comp));
        p.add(showHideButton, BorderLayout.SOUTH);

        add(p);
        setPreferredSize(new Dimension(320, 240));
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


        frame.getContentPane().add(new CollapsiblePanel( pane_base, pane_upper));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class ControlsBorderLayout extends BorderLayout {
    public boolean isHidden = true;
    private final Timer animator;
    private int controlsHeight;

    public ControlsBorderLayout(Timer animator) {
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
