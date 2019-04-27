FROM tomcat

COPY target/priceprediction.war /usr/local/tomcat/webapps/priceprediction.war

# local application port

# execute it