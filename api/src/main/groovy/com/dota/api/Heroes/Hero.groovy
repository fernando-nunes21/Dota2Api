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

    void setId(Integer id) {
        this.id = id
    }

    void setName(String name) {
        this.name = name
    }

    void setLane(String lane) {
        this.lane = lane
    }

    void setDifficult(String difficult) {
        this.difficult = difficult
    }

    void setSkills(List<String> skills) {
        this.skills = skills
    }

    void setSkins(List<String> skins) {
        this.skins = skins
    }
}
