// Copyright (C) 2013 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.gerrit.server.project;

import com.google.common.base.Strings;
import com.google.gerrit.extensions.common.ProjectInfo;
import com.google.gerrit.extensions.restapi.Url;
import com.google.gerrit.reviewdb.client.Project;
import com.google.gerrit.server.config.AllProjectsName;
import com.google.inject.Inject;

public class ProjectJson {

  private final AllProjectsName allProjects;

  @Inject
  ProjectJson(AllProjectsName allProjects) {
    this.allProjects = allProjects;
  }

  public ProjectInfo format(ProjectResource rsrc) {
    return format(rsrc.getControl().getProject());
  }

  public ProjectInfo format(Project p) {
    ProjectInfo info = new ProjectInfo();
    info.name = p.getName();
    Project.NameKey parentName = p.getParent(allProjects);
    info.parent = parentName != null ? parentName.get() : null;
    info.description = Strings.emptyToNull(p.getDescription());
    info.state = p.getState();
    info.id = Url.encode(info.name);
    return info;
  }
}
