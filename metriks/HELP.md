# Guides

Servo provides four primary metric types: Counter, Gauge, Timer, and Informational.
BasicCounter does what a counter should do, plain and straightforward
PeakRateCounter returns the maximum count for a given second during the polling interval
Unlike other counters, StepCounter records rate per second of previous polling interval:

Gauge is a simple monitor that returns the current value. BasicGauge, MinGauge, MaxGauge, and NumberGauges are provided.
MaxGauge and MinGauge are used to keep track of the maximum and minimum values respectively
NumberGauge (LongGauge, DoubleGauge) wraps a provided Number (Long, Double). To collect metrics using these gauges, we must ensure the Number is thread-safe.

Timers help measure duration of a particular event. Default implementations are BasicTimer, StatsTimer, and BucketTimer.
BasicTimer records total time, count and other simple statistics
StatsTimer provides much richer statistics by sampling between polling intervals
BucketTimer provides a way to get the distribution of samples by bucketing value ranges:

We can make use of the Informational monitor to record descriptive information to help debugging and diagnostics. The only implementation is BasicInformational
The metric types are all of type Monitor, which is the very base of Servo. We now know kinds of tools collect raw metrics, but to report the data, we need to register these monitors.
Most of the time, we can use DefaultMonitorRegistry to register monitors, to dynamically register a monitor, DynamicTimer, and DynamicCounter can be used

Atlas is metrics-related tool from Netflix. It's a tool for managing dimensional time series data, which is a perfect place to publish the metrics we collected.
set up an Atlas server:

``
$ curl -LO 'https://github.com/Netflix/atlas/releases/download/v1.4.4/atlas-1.4.4-standalone.jar'
$ curl -LO 'https://raw.githubusercontent.com/Netflix/atlas/v1.4.x/conf/memory.conf'
$ java -jar atlas-1.4.4-standalone.jar memory.conf
``

The AtlasMetricObserver requires a simple configuration and a list of tags. Metrics of the given tags will be pushed to Atlas:

``
System.setProperty("servo.pollers", "1000");
System.setProperty("servo.atlas.batchSize", "1");
System.setProperty("servo.atlas.uri", "http://localhost:7101/api/v1/publish");
AtlasMetricObserver observer = new AtlasMetricObserver(
  new BasicAtlasConfig(), BasicTagList.of("servo", "counter"));
PollRunnable task = new PollRunnable(new MonitorRegistryMetricPoller(), new BasicMetricFilter(true), observer);
``