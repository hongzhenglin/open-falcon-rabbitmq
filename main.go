package main

import (
	"github.com/Cepave/open-falcon-backend/common/logruslog"
	"github.com/Cepave/open-falcon-backend/common/vipercfg"
	"github.com/Cepave/open-falcon-backend/modules/consumer/g"
	"github.com/Cepave/open-falcon-backend/modules/consumer/receive"
)

func main() {
	vipercfg.Parse()
	vipercfg.Bind()
	vipercfg.Load()
	g.ParseConfig(vipercfg.Config().GetString("config"))
	logruslog.Init()
	receive.Consume()
}
