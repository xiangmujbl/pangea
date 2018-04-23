<adf-curation>
    <curation mainRegion="" viewRegion="/${system}/${_name}" chunkSize="1000" chunkBytes="40096"
              executionClassName="com.jnj.pangea.${system}.${_name}.controller.${fullName}Controller">
        <regions>

            <region path="">
                <columns></columns>
                <relations>
                    <relation path="" isPrimaryKey="false">
                        <expression></expression>
                    </relation>
                </relations>
            </region>

            <region path="/${system}/${_name}">
                <columns></columns>
            </region>

        </regions>
    </curation>
</adf-curation>