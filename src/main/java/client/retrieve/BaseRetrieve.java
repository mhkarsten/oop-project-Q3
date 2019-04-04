package client.retrive;

import client.Service.MyRestTemplate;

abstract public class BaseRetrieve {

    protected MyRestTemplate restTemplate;

    public void setRestTemplate(MyRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BaseRetrieve() {
        this.restTemplate = new MyRestTemplate();
    }
}
