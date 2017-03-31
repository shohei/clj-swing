(ns clj-swing.core)

(import '(javax.swing JFrame JPanel JButton JOptionPane))

(defmacro on-action [component event & body]
  `(. ~component addActionListener
      (proxy [java.awt.event.ActionListener] []
        (actionPerformed [~event] ~@body))))

(defn show-dialog []
  (JOptionPane/showMessageDialog nil "Hello World"))

(defn -main []
  (def frame (JFrame. "Hello Frame"))
  (.setSize frame 200 200)
  (.setVisible frame true)
  (def panel (JPanel.))
  (.setContentPane frame panel)
  (def button (JButton. "Click Me!"))
  (.add panel button)
  (doto button 
    (on-action event 
               (show-dialog)))
  (.revalidate button)
  (while 1))

