# addressPlugin
a cordova plugin use BaiduMap to change latitude and longitude to address

attention： you need make sure open build.gradle add
dependencies {
  compile 'org.jbundle.util.osgi.wrapped:org.jbundle.util.osgi.wrapped.org.apache.http.client:4.1.2'
}

cordova.plugins.addressPlugin.address(YourBaiduMapBrowserK,latitude,longitude,result =>{
      console.log("result:",result);
    },error =>console.log("error:",error));
