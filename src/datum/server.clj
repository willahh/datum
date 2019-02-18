(ns datum.server
  (:require [datahike.api :as d]
            [datum.domain.user-schema :as user-schema]
            [datum.domain.media-schema :as media-schema]))

(def uri "datahike:file:///tmp/datum")

(def schema (merge {}
                   media-schema/schema
                   user-schema/schema))

;; (d/delete-database uri)
(d/create-database-with-schema uri schema)


