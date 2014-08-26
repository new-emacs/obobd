/*
 *   Copyright 2012 OSBI Ltd
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.saiku.repository;


import org.saiku.datasources.connection.RepositoryFile;
import org.saiku.service.user.UserService;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.io.IOException;
import java.util.List;

/**
 * Repository Manager Interface
 */
public interface IRepositoryManager {
  void init();

  boolean start(UserService userService) throws RepositoryException;

  void createUser( String u ) throws RepositoryException;

  javax.jcr.NodeIterator getHomeFolders() throws RepositoryException;

  javax.jcr.Node getHomeFolder( String directory ) throws RepositoryException;

  javax.jcr.Node getFolder( String user, String directory ) throws RepositoryException;

  void shutdown();

  boolean createFolder( String username, String folder ) throws RepositoryException;

  boolean deleteFolder( String folder ) throws RepositoryException;

  void deleteRepository() throws RepositoryException;

  boolean moveFolder(String user, String folder, String source, String target) throws RepositoryException;

    javax.jcr.Node saveFile(Object file, String path, String user, String type, List<String> roles) throws RepositoryException;

    void removeFile(String path, String user, List<String> roles) throws RepositoryException;

    void moveFile(String source, String target, String user, List<String> roles) throws RepositoryException;


    javax.jcr.Node saveInternalFile(Object file, String path, String type) throws RepositoryException;

    String getFile(String s, String username, List<String> roles) throws RepositoryException;

    String getInternalFile(String s) throws RepositoryException;


    List<org.saiku.database.dto.MondrianSchema> getAllSchema() throws RepositoryException;

    List<DataSource> getAllDataSources() throws RepositoryException;

    void saveDataSource(DataSource ds, String path, String user) throws RepositoryException;

    byte[] exportRepository() throws RepositoryException, IOException;

    void restoreRepository(String xml) throws RepositoryException, IOException;

    RepositoryFile getFile(String fileUrl);

    List<IRepositoryObject> getAllFiles(String type, String username, List<String> roles) throws RepositoryException;

    void deleteFile(String datasourcePath);

    AclEntry getACL(String object, String username, List<String> roles);

    void setACL(String object, String acl, String username, List<String> roles) throws RepositoryException;

    List<org.saiku.database.dto.MondrianSchema> getInternalFilesOfFileType(String type) throws RepositoryException;

    void createFileMixin(String type) throws RepositoryException;
}
