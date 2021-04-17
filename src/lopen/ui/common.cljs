(ns lopen.ui.common)

(defn button [opts & children]
  [:button.bg-green-400.rounded.py-2.px-10.font-semibold.dark:text-white.dark:bg-green-600
   opts children])

(defn textarea [opts]
  [:textarea.border.border-gray-300.rounded.dark:bg-gray-900.dark:border-gray-900.my-3
   opts])
