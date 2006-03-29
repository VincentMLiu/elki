package de.lmu.ifi.dbs.featureextraction.image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes the extracted festures in txt format to output.
 * 
 * @author Elke Achtert (<a
 *         href="mailto:achtert@dbs.ifi.lmu.de">achtert@dbs.ifi.lmu.de</a>)
 */
class FeatureTxtWriter extends FeatureWriter
{

    /**
     * Creates a new FeatureTxtWriter and initializes it with the specified
     * parameters.
     * 
     * @param outputDir
     *            the name of the output directory
     * @param classIDs
     *            a string representation of the class ids of the images
     * @throws java.io.IOException
     */
    FeatureTxtWriter(String outputDir, String classIDs) throws IOException
    {
        super(classIDs);

        // create output directory
        File dir = new File(outputDir);
        if (!dir.exists())
        {
            dir.mkdir();
        }

        // color histogram
        String fileName = outputDir + File.separator
                + ImageDescriptor.featureNames[0];

        colorHistogramWriter = new BufferedWriter(new FileWriter(fileName
                + ".txt"));
        writeHeader(colorHistogramWriter, ImageDescriptor.featureNames[0],
                ImageDescriptor.numAttributes[0]);

        // color moments
        for (int i = 0; i < colorMomentsWriters.length; i++)
        {
            // parent directory
            fileName = outputDir + File.separator
                    + ImageDescriptor.featureNames[i + 1];

            colorMomentsWriters[i] = new BufferedWriter(new FileWriter(fileName
                    + ".txt"));
            writeHeader(colorMomentsWriters[i],
                    ImageDescriptor.featureNames[i + 1],
                    ImageDescriptor.numAttributes[i + 1]);
        }

        // texture features
        for (int i = 0; i < textureFeatureWriters.length; i++)
        {
            // parent directory
            fileName = outputDir + File.separator
                    + ImageDescriptor.featureNames[i + 4];

            textureFeatureWriters[i] = new BufferedWriter(new FileWriter(
                    fileName + ".txt"));
            writeHeader(textureFeatureWriters[i],
                    ImageDescriptor.featureNames[i + 4],
                    ImageDescriptor.numAttributes[i + 4]);
        }

        // roughness statictics
        for (int i = 0; i < roughnessStatsWriters.length; i++)
        {
            // parent directory
            fileName = outputDir + File.separator
                    + ImageDescriptor.featureNames[i + 17];

            roughnessStatsWriters[i] = new BufferedWriter(new FileWriter(
                    fileName + ".txt"));
            writeHeader(roughnessStatsWriters[i],
                    ImageDescriptor.featureNames[i + 17],
                    ImageDescriptor.numAttributes[i + 17]);
        }

        // facet-orientation statistics
        for (int i = 0; i < facetStatsWriters.length; i++)
        {
            // parent directory
            fileName = outputDir + File.separator
                    + ImageDescriptor.featureNames[i + 25];

            facetStatsWriters[i] = new BufferedWriter(new FileWriter(fileName
                    + ".txt"));
            writeHeader(facetStatsWriters[i],
                    ImageDescriptor.featureNames[i + 25],
                    ImageDescriptor.numAttributes[i + 25]);
        }
    }

    /**
     * Writes the header of the arff-file
     * 
     * @param writer
     *            the writer to write on
     * @param featureName
     *            the name of the feature to be written
     * @param numAttributes
     *            the number of attributes of the feature
     * @throws java.io.IOException
     */
    private void writeHeader(BufferedWriter writer, String featureName,
            int numAttributes) throws IOException
    {

        writer
                .write("#########################################################");
        writer.newLine();
        writer.write("### Feature " + featureName);
        writer.newLine();
        writer.write("### attribute id string");
        writer.newLine();
        for (int j = 0; j < numAttributes; j++)
        {
            writer.write("### attribute d" + j + " numeric");
            writer.newLine();
        }
        writer.write("### attribute class {" + classIDs + "}");
        writer.newLine();
        writer
                .write("#########################################################");
        writer.newLine();
        writer.newLine();
    }

}
