(ns lopen.ui.common)

(defn button [opts & children]
  [:button.bg-green-400.rounded.py-2.px-10.font-semibold.text-gray-800.focus:ring-blue-600.focus:ring-2
   opts children])

(defn textarea [opts]
  [:textarea.border.border-gray-300.rounded.dark:bg-gray-900.dark:border-gray-700.my-3
   opts])

(defn card [opts & children]
  [:div.bg-gray-100.dark:bg-gray-800.py-5.px-6.mb-5.rounded.shadow-sm
   opts children])
