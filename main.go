package main

import (
	"github.com/Cepave/open-falcon-backend/common/logruslog"
	"github.com/Cepave/open-falcon-backend/common/vipercfg"
	"github.com/humorless/open-falcon-rabbitmq/g"
	"github.com/humorless/open-falcon-rabbitmq/receive"
)

func main() {
	vipercfg.Parse()
	vipercfg.Bind()
	vipercfg.Load()
	g.ParseConfig(vipercfg.Config().GetString("config"))
	logruslog.Init()
	receive.Consume()
}
