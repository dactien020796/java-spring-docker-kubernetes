# java-spring-docker-kubernetes

This repo is a learning project of a Udemy course [Master Microservices with Java, Spring, Docker, Kubernetes](https://www.udemy.com/course/master-microservices-with-spring-docker-kubernetes/)

### 08/07/2023: Demo for microservices distributed patterns
This demo contains:
- Splunk: Log tracing
  - We're using pull model by having a splunkforwarder sidecar to forward all application logs to Splunk server. When running on K8s, this splunkforwarder will be installed on the cluster
- Micrometer + Prometheus + Grafana: Monitor application metrics
  - Micrometer is a metrics collection/exposition library for Java. It provides a uniform way to instrument Java applications and collect metrics from a variety of sources
  - Prometheus is a time-series database, which save all metrics
  - Grafana is the open source analytics & monitoring solution for every database (Prometheus, Graphite,...)
  - Even Prometheus support Prometheus JMX Exporter or Prometheus Java client. We prefer using <b>micrometer.io</b> as a abstract layer, to capture metrics and expose them to several different tools – including Prometheus
  - Some Grafana dashboard that could be imported:
    - https://grafana.com/grafana/dashboards/11378-justai-system-monitor/
    - https://grafana.com/grafana/dashboards/14430-spring-boot-statistics-endpoint-metrics/
- Zipkin/Spring cloud Sleuth: Distributed tracing

How to start:
- `mvn clean package` for accounts/cards/loans
- `docker compose up` to start 3 micro-services & infrastructure containers
- Generate some logs by `curl http://localhost:8080/accounts?customerId=1` or `curl localhost:8080/accounts/detail?customerId=1`

Reference documents:
- Micrometer: https://www.baeldung.com/micrometer