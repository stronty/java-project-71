.PHONY:build

run:
	gradle run
run-dist:
	./build/install/app/bin/app
h:
	gradle installDist
	./build/install/app/bin/app -h
build:
	gradle build

complete:
	gradle build
	gradle install
