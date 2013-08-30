TARGET=/usr/local/share/jdecompiler

all: jd-core-java.jar nativelib

nativelib:
#	hg clone https://bitbucket.org/bric3/jd-intellij

jd-core-java.jar:
	mvn package && cp target/jd-core-java-*.jar $@

clean:
	rm -rf jd-core-java.jar target

install:
	[ -e "${TARGET}" ] && rm -r "${TARGET}"
	mkdir "${TARGET}" \
		&& cp -R jd-core-java.jar nativelib jdecompiler "${TARGET}" \
		&& ln -s -f "${TARGET}/jdecompiler" /usr/local/bin/jdecompiler
