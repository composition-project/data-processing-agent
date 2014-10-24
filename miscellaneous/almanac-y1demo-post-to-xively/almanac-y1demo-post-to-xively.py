import xively
import datetime
import sys
import time
import xml.etree.ElementTree as etree
from Bin import *
	
BINS = {
	'B': Bin('B','561056653','HZr68o7VWYaYbr1NV0YVkwQjPYgQei8UlLkdbCvl7QKnsAzF'),
	'C': Bin('C','488920744','CwW3flZOE0AS8in6R05wKBCXYcimJ04zWIDBJFrZT5Byy9JF'),
	'D': Bin('D','675560879','VEAoRaIYPfL8rIjXoNJT5lFpU9TqGYrhWBHnUwB72KR4INda'),
	'E': Bin('E','1987314073','NTKcEStj5JvADeMNMa58kVqMr0rsOjHzik5k7ZBWfbvcbq4l')
	};	

active=True;

SLEEP_TIME_SECS=1;



def main():
	i=0;
	while(active):
		print(str(i) + ': tick')
		
		#updating all bins
		for mybin in BINS.values():
			mybin.update();
			print('\t' + str(mybin));
			api = xively.XivelyAPIClient(mybin.api_key);
			feed = api.feeds.get(mybin.feed_id);
			now = datetime.datetime.utcnow();
			feed.datastreams = [
				xively.Datastream(id='fillLevel', current_value=mybin.fillLevel, at=now),
				]
			feed.update()
		
		
		i+=1;
		time.sleep(Bin.UPDATETIME_secs);


if __name__ == '__main__':
    try:
        args = sys.argv[1:]
        main(*args)
    except KeyboardInterrupt:
        pass

print('Exiting.');