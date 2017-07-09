package com.treebo.espressocommons.testrunner;

import com.treebo.espressocommons.configurations.BaseTest;
import com.treebo.espressocommons.testrunner.executiontypes.High;
import com.treebo.espressocommons.testrunner.executiontypes.Sanity;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by kishorepolisetty on 06/03/17.
 */

/**
 * This is sample Test Suite class for a project.
 * eg: Using annotation @Categories to identify the category and run as a test suite for the given category.
 **/

@RunWith(Categories.class)
@Categories.IncludeCategory({Sanity.class})
@Categories.ExcludeCategory({High.class})
@Suite.SuiteClasses(
        {
                // All Classes
        })
public class SampleSuiteClass extends BaseTest {

}
