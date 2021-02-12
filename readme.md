## My Voucher shop


### Tests

![Java CI vouchershop](https://github.com/kzlamaniec/vouchershop/workflows/Java%20CI%20vouchershop/badge.svg)

in order to perform tests on your own, fell free to use

```bash
maven test
```

### Ci via github actions

* https://github.com/actions/starter-workflows/blob/c59b62dee0eae1f9f368b7011cf05c2fc42cf084/ci/maven.yml
* https://github.com/actions/create-release
* https://github.com/actions/upload-release-asset

### testing via CURL 

#### CRUD
##### Create
add client
```bash
curl -X POST http://localhost:8080/api/clients -H 'content-type: application/json' -d '{"firstname": "Klaudia", "lastname": "Zlamaniec", "address": {"street": "rakowicka", "zip": "31-222", "city": "krakow"}}'

# in Windows
curl -X POST http://localhost:8080/api/clients -H "content-type: application/json" -d "{\"firstname\": \"Klaudia\", \"lastname\": \"Zlamaniec\",
 \"address\": {\"street\": \"rakowicka\", \"zip\": \"31-222\", \"city\": \"krakow\"}}"
```

##### Read
get single client
```bash
curl http://localhost:9999/api/clients/3 
```

get list
```bash
curl http://localhost:9999/api/clients 
```

##### Update
update by id
```bash
curl -X POST http://localhost:9999/api/clients/4 -H 'content-type: application/json' -d '{"firstname": "Katarzyna", "lastname": "Krawiec", "address": {"street": "nibylandia", "zip": "31-222", "city": "whatever"}}'
```

##### Delete
delete by id
```bash
curl -X DELETE http://localhost:9999/api/clients/1 
```