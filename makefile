build:
	docker build . -t axreng/backend
run: 
	docker run -v "${HOME}/.m2":/root/.m2 -e BASE_URL=http://hiring.axreng.com/ -e KEYWORD=four -e MAX_RESULTS=-1 --rm axreng/backend 