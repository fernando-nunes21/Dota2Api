package com.dota.api.Heroes

class Hero {
    private Integer id
    private String name
    private String lane
    private String difficult
    private List<String> skills
    private List<String> skins

    String getName() {
        return name
    }

    String getLane() {
        return lane
    }

    String getDifficult() {
        return difficult
    }

    List<String> getSkills() {
        return skills
    }

    List<String> getSkins() {
        return skins
    }
}
