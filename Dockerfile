FROM jboss/wildfly

MAINTAINER Jure Frank version: 1.0

ADD build/praktikumEAR.ear/ /opt/jboss/wildfly/standalone/deployments/praktikumEAR.ear
ADD WebContent/ /opt/jboss/wildfly/standalone/deployments/praktikumEAR.ear/praktikum-II.war/
ADD build/classes /opt/jboss/wildfly/standalone/deployments/praktikumEAR.ear/praktikum-II.war/WEB-INF/classes
ADD /build/praktikumEAR.ear.dodeploy /opt/jboss/wildfly/standalone/deployments/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
#ENTRYPOINT /bin/bash