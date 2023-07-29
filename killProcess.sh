ps -ef | grep java | grep -v grep | awk '{print $2}' | xargs kill
ps -ef | grep DDoS | grep -v grep | awk '{print $2}' | xargs kill