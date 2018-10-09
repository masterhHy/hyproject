/**
 * 把系统常量挂在wx对象
 * 把全局方法也挂wx对象上
 */



let install=function(wx){
  wx.app_mainColor="#409eff";
  axios.wx=wx;
  wx.axios = axios;
}

//封装request 做全局拦截
let baseURL = '';
let axios={
  wx:"",
  get(url, params) {
    return this.apiTem(url, params, "GET")
  },
  post(url, params) {
    return this.apiTem(url, params, "POST")
  },
  apiTem: function (url, params, method) {
    return new Promise(function (resolve, reject) {
      axios.wx.showLoading();
      axios.wx.request({
        url: url,
        method: method,
        header: {
          FR_APPID: axios.wx.extAppid || '',
          FR_OPEN_ID: axios.wx.openid || '',
          FR_LOGIN_TICKET: axios.wx.ticket || ''
        },
        data: params||{},
        success(res) {
          setTimeout(function(){
            wx.hideLoading();
          },500);
          //如果用户没登录，跳转到登陆页面


          resolve(res);
          
        },
        fail(res) {
          setTimeout(function () {
            wx.hideLoading();
          }, 500);
          console.log(window)
          reject(res);
          
        }
      })
    })
  }
}




module.exports = {
  install: install
}
