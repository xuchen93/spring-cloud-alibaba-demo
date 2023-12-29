## docker启动
```bash
docker run --name seata-server -p 8091:8091 -p 7091:7091  -v ${PWD}/resources:/seata-server/resources -v ${PWD}/logs:/root/logs/seata -e TZ=Asia/Shanghai -e SEATA_IP=124.220.50.39  -d  seataio/seata-server:1.7.0
```
其中resources下的application.yml文件使用当前目录下的[application.yml](application.yml)

## 同步seata-server配置到nacos
```bash
sh nacos-config.sh -h 124.220.50.39 -p 8848 -g SEATA_GROUP -t 426b74f2-7284-4d25-8828-0cc75e3013c2
 -u nacos -w nacos
```
