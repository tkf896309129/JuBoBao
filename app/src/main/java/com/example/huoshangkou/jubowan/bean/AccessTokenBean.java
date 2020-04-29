package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：AccessTokenBean
 * 描述：
 * 创建时间：2019-08-22  09:24
 */

public class AccessTokenBean {


    /**
     * refresh_token : 25.577676fe2875f04cae4f4d3ddc1d07f1.315360000.1881797066.282335-17006445
     * expires_in : 2592000
     * session_key : 9mzdDZL9qerk1a46puwFPTRCG7bwmgKTNO+Vkt/8lOnok9wulBxtTVPGFzwpK2+GFIJNol5bph/NvN5C02Myuk0jrRyCmA==
     * access_token : 24.6df163af2e0d1362c403c336b3d071d9.2592000.1569029066.282335-17006445
     * scope : audio_voice_assistant_get brain_enhanced_asr audio_tts_post public brain_all_scope picchain_test_picchain_api_scope wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test权限 vis-classify_flower lpq_开放 cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base iop_autocar oauth_tp_app smartapp_smart_game_openapi oauth_sessionkey smartapp_swanid_verify smartapp_opensource_openapi smartapp_opensource_recapi fake_face_detect_开放Scope
     * session_secret : 5a8f635d79b025f3dd656757ab5e2d9c
     */

    private String refresh_token;
    private int expires_in;
    private String session_key;
    private String access_token;
    private String scope;
    private String session_secret;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSession_secret() {
        return session_secret;
    }

    public void setSession_secret(String session_secret) {
        this.session_secret = session_secret;
    }
}
