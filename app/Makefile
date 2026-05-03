run:
	gradle run
run-dist:
	./build/install/app/bin/app
i:
	gradle installDist
	./build/install/app/bin/app -h
startOver:
	rm -rf ./build
	make i
	make run