import java.util.Stack;

import javax.swing.JFrame;

public class FramesStack {

    private int size;
    private Stack<JFrame> frames;

    public FramesStack() {
        frames = new Stack<JFrame>();
        size = frames.size();
    }

    public int size() {
        return size;
    }

    public void push(JFrame frame) {
        frames.push(frame);
        size++;
    }
    
    public JFrame pop() {
        if (frames.isEmpty()) {
            return new JFrame();
        }
        return frames.pop();
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
}
