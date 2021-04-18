(ns lopen.ui.common)

(defn- siblings [children]
  (vec (cons :<> children)))

(defn- with-react-keys [children]
  (siblings
   (for [[i child] (map-indexed vector children)]
     (with-meta child {:key i}))))

(defn button [opts & children]
  [:button.bg-green-400.rounded.py-2.px-10.font-semibold.text-gray-800.focus:ring-blue-600.focus:ring-2
   opts children])

(defn textarea [opts]
  [:textarea.border.border-gray-300.rounded.dark:bg-gray-900.dark:border-gray-700.my-3
   opts])

(defn card [opts & children]
  [:div.bg-gray-100.dark:bg-gray-800.py-5.px-6.mb-5.shadow-sm
   opts (with-react-keys children)])

(def close-icon
  [:svg {:width "24px"
         :height "24px"
         :viewBox "0 0 24 24"
         :fill "#eeeeee"}
   [:path {:d "M13.41 12l4.3-4.29a1 1 0 1 0-1.42-1.42L12 10.59l-4.29-4.3a1 1 0 0 0-1.42 1.42l4.3 4.29-4.3 4.29a1 1 0 0 0 0 1.42 1 1 0 0 0 1.42 0l4.29-4.3 4.29 4.3a1 1 0 0 0 1.42 0 1 1 0 0 0 0-1.42z"}]])
