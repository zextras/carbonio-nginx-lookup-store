// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: AGPL-3.0-only

package com.zimbra.cs.account;

public class UnitTestAccount extends Account {
    private Server server = null;
    private String id = null;

    public UnitTestAccount(String name, String id, Provisioning prov, Server server) {
        super(name, id, null, null, prov);
        this.id = id;
        this.server = server;
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public String getId() {
        return id;
    }
}
