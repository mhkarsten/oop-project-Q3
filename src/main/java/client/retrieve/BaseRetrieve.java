package client.retrieve;

import client.service.MyRestTemplate;

public abstract class BaseRetrieve {

    protected MyRestTemplate restTemplate;

    public BaseRetrieve() {
        this.restTemplate = new MyRestTemplate();
    }

    public void setRestTemplate(MyRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
