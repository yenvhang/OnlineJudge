#!/bin/sh
submissionId=$1
checkPointId=$2
java -cp /var/OnlineJudge/code/$1/ Main </var/OnlineJudge/checkPoints/input$2.txt >>/var/OnlineJudge/temp/$1output$2.txt 2>/var/OnlineJudge/error/$1err_run_log$2.txt

