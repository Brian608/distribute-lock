package org.feather.distributelock.dao;


import org.apache.ibatis.annotations.Param;
import org.feather.distributelock.model.DistributeLock;
import org.feather.distributelock.model.DistributeLockExample;

import java.util.List;

public interface DistributeLockMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    long countByExample(DistributeLockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    int deleteByExample(DistributeLockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    int insert(DistributeLock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    int insertSelective(DistributeLock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    List<DistributeLock> selectByExample(DistributeLockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    DistributeLock selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    int updateByExampleSelective(@Param("record") DistributeLock record, @Param("example") DistributeLockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    int updateByExample(@Param("record") DistributeLock record, @Param("example") DistributeLockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    int updateByPrimaryKeySelective(DistributeLock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table distribute_lock
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    int updateByPrimaryKey(DistributeLock record);

    DistributeLock selectDistributeLock(@Param("businessCode") String businessCode);
}