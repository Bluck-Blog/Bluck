#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)


IDLE_PID=$(sudo lsof -ti tcp:8080)

if [ -z ${IDLE_PID} ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> sudo kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi