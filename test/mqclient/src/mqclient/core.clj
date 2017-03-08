(ns mqclient.core
  (:gen-class)
  (:require [langohr.core      :as rmq]
            [langohr.channel   :as lch]
            [langohr.queue     :as lq]
            [langohr.consumers :as lc]
            [langohr.basic     :as lb]
            [clojure.data.json :as json]))

(def ^{:const true}
  default-exchange-name "")

(defn message-handler
  [ch {:keys [content-type delivery-tag type] :as meta} ^bytes payload]
  (println (format "[consumer] Received a message: %s, delivery tag: %d, content type: %s, type: %s"
                   (String. payload "UTF-8") delivery-tag content-type type)))

(def message 
     {:endpoint "test-endpoint"
      :metric "test-metric"
      :timestamp 1488884708
      :step 60
      :value 10
      :counterType "GAUGE"
      :tags "idc=lg"})

(defn -main
  [& args]
  (let [conn  (rmq/connect)
        ch    (lch/open conn)
        qname "extend"]
    (println (format "[main] Connected. Channel id: %d" (.getChannelNumber ch)))
    (lq/declare ch qname {:durable true :exclusive false :auto-delete false})
;;    (lc/subscribe ch qname message-handler {:auto-ack true})
    (lb/publish ch default-exchange-name qname (json/write-str message) {:content-type "text/plain" :type "greetings.hi"})
    (Thread/sleep 2000)
    (println "[main] Disconnecting...")
    (rmq/close ch)
    (rmq/close conn)))
