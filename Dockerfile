FROM tomcat:9.0.16-jre8
ADD test.war /usr/local/tomcat/webapps/
COPY ["sqlite/", "/var/lib/sqlite/"]
#RUN sudo apt-get install -y sqlite3 libsqlite3-dev

